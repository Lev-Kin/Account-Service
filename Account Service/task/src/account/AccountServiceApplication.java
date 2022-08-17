package account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    //authn
    @PostMapping("/api/auth/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequestDto signUpModel) {
        if(signUpModel.getEmail()==null || signUpModel.getEmail().isBlank() || signUpModel.getEmail().isEmpty() ||
                signUpModel.getName() == null ||signUpModel.getName().isBlank() || signUpModel.getName().isEmpty() ||
                signUpModel.getLastname() == null ||signUpModel.getLastname().isBlank() || signUpModel.getLastname().isEmpty() ||
                signUpModel.getPassword() == null ||signUpModel.getPassword().isBlank() || signUpModel.getPassword().isEmpty() ||
                !signUpModel.getEmail().contains("@acme.com") ) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setTimestamp(LocalDate.now().toString());
            errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
            errorDto.setError("Bad Request");
            errorDto.setPath("/api/auth/signup");
            return new ResponseEntity(errorDto,HttpStatus.BAD_REQUEST);
        }
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto(){        };
        signUpResponseDto.setName(signUpModel.getName());
        signUpResponseDto.setLastname(signUpModel.getLastname());
        signUpResponseDto.setEmail(signUpModel.getEmail());
        return new ResponseEntity(signUpResponseDto, HttpStatus.OK);

    }

    @PostMapping("/api/auth/changepass")
    public void changePass() {    }

    // business
    @GetMapping("/api/empl/payment")
    public void getEmployeePayroll(){}

    @PostMapping("/api/acct/payments")
    public void uploadPayrolls() {}

    @PutMapping("/api/acct/payments")
    public void updatePaymentInfo() {}


    // service
    @PutMapping("/api/admin/user/role")
    public void changeUserRole(){}

    @DeleteMapping("/api/admin/user")
    public void deleteAUser() {}

    @GetMapping("/api/admin/user")
    public void displayUserInfo() {}
}

