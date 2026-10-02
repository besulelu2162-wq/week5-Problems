public class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(
            String bookingId,
            String[] seatNumbers) {

        this.bookingId = bookingId;

        // Defensive copy
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {

        // Defensive copy
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(
            int index,
            String newSeat) {

        // Create a copy
        String[] updatedSeats =
                seatNumbers.clone();

        updatedSeats[index] = newSeat;

        // Return a NEW object
        return new BookingReceipt(
            bookingId,
            updatedSeats
        );
    }

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {

                nullSkipped++;

            } else {

                processed++;

                if (receipt instanceof GroupBookingReceipt) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + groupCount
                + " group | "
                + individualCount
                + " individual";
    }

    public static void main(String[] args) {

        BookingReceipt b =
            new BookingReceipt(
                "CH-1001",
                new String[]{"A1", "A2"}
            );

        // Test defensive copy from getter
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";

        System.out.println(
            "Original first seat: "
            + b.getSeatNumbers()[0]
        );

        // Test withUpdatedSeat()
        BookingReceipt updated =
                b.withUpdatedSeat(1, "A3");

        System.out.println(
            "Original seats: "
            + String.join(
                ", ",
                b.getSeatNumbers()
            )
        );

        System.out.println(
            "Updated seats: "
            + String.join(
                ", ",
                updated.getSeatNumbers()
            )
        );

        // Test nightly settlement
        BookingReceipt[] receipts = {

            new GroupBookingReceipt(
                "CH-2002",
                new String[]{"B1", "B2"},
                2
            ),

            null,

            new BookingReceipt(
                "CH-3003",
                new String[]{"C1"}
            )
        };

        System.out.println(
            processNightlySettlement(receipts)
        );
    }
}


// Group booking variant
class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);

        this.groupSize = groupSize;
    }
}