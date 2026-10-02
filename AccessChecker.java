public class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        boolean allowed = false;

        // Normal access rules
        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                allowed = true;
            }

        } else if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                allowed = true;
            }

        } else if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                allowed = true;
            }

        } else if (fieldModifier.equals("public")) {

            allowed = true;
        }

        // M2: subclass in a different package
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                allowed = true;
            }

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                allowed = false;
            }
        }

        // private and default are not accessible
        // from a subclass in another package
        if (fieldModifier.equals("private")
                || fieldModifier.equals("default")) {

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
                    || accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

                allowed = false;
            }
        }

        return allowed ? "ALLOWED" : "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result = classifyAccess(
                    attempt[0],
                    attempt[1]
            );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed
                + " | Denied: " + denied;
    }

    public static void main(String[] args) {

        // M1 examples
        System.out.println(
            classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            summarizeBatch(attempts)
        );

        // M2 examples
        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}


/*
 * MovieTicket class required by the problem.
 */
class MovieTicket {

    private String seatNumber;

    String screenId;

    protected double ticketPrice;

    public String movieTitle;
}