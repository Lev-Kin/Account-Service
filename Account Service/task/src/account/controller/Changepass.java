package account.controller;

public record Changepass(String email) {
    public Changepass(String email) {
        this.email = email.toLowerCase();
    }

    public String getStatus() {
        return "The password has been updated successfully";
    }
}

