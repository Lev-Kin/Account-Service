package account.business;

import account.repository.SalaryRepository;
import account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentServices {
    private final SalaryRepository salaries;
    private final UserRepository users;

    @Autowired
    public PaymentServices(SalaryRepository salaries, UserRepository users) {
        this.salaries = salaries;
        this.users = users;
    }

    public UploadSuccess upload(List<Salary> salaryList) {
        for (Salary s : salaryList) {
            if (salaries.findByEmployeeAndPeriod(s.getEmployee().toLowerCase(), s.getPeriod()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        salaries.saveAll(salaryList);
        return new UploadSuccess();
    }

    public UpdateSuccess update(Salary salary) {
        salaries.save(salary);
        return new UpdateSuccess();
    }

    public List<SalaryOut> get(String email) {
        List<Salary> salaryList = salaries.findByEmployeeOrderByPeriodDesc(email).orElse(null);
        List<SalaryOut> outList = new ArrayList<>();
        if (salaryList == null) {
            return outList;
        }
        User user = users.findByEmailIgnoreCase(email).get();
        String name = user.getName();
        String lastname = user.getLastname();
        for (Salary s : salaryList) {
            YearMonth period = s.getPeriod();
            long salary = s.getSalary();
            outList.add(new SalaryOut(name, lastname, period, salary));
        }
        return outList;
    }

    public SalaryOut get(String email, String period) {
        DateTimeFormatter in = DateTimeFormatter.ofPattern("MM-uuuu");
        YearMonth yearMonth = YearMonth.parse(period, in);
        Salary salary = salaries.findByEmployeeAndPeriod(email, yearMonth).orElse(null);
        if (salary == null) {
            return null;
        }
        User user = users.findByEmailIgnoreCase(email).get();
        String name = user.getName();
        String lastname = user.getLastname();
        return new SalaryOut(name, lastname, yearMonth, salary.getSalary());
    }
}


