package account.business.response;

public record DeleteSuccess(String user) {

    public String getStatus() {
        return "Deleted successfully!";
    }
}
