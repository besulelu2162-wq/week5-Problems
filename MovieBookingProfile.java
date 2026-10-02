public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public MovieBookingProfile() {
        confirmed = false;
    }

    // Constructor chaining
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    // Boolean getter
    public boolean isConfirmed() {
        return confirmed;
    }

    // Setter
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only OTP
    public void setOtp(String otp) {

        if (otp != null
                && otp.matches("\\d{4,6}")) {

            this.otp = otp;
        }
    }

    public static void main(String[] args) {

        MovieBookingProfile profile =
                new MovieBookingProfile("Rahul Dev");

        System.out.println(
            profile.getName()
        );

        profile.setConfirmed(true);

        System.out.println(
            profile.isConfirmed()
        );

        profile.setOtp("4471");

        System.out.println(
            "OTP set successfully"
        );
    }
}