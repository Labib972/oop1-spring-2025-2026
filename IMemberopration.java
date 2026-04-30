// IMemberOperation.java
interface IMemberOperation {
    double discountedFee();
}

// Member.java
abstract class Member {
    protected String memberID;
    protected double monthlyFee;

    public Member() {}

    public Member(String memberID, double monthlyFee) {
        this.memberID = memberID;
        this.monthlyFee = monthlyFee;
    }

    public abstract void showInfo();
}

// PlatinumMember.java
class PlatinumMember extends Member implements IMemberOperation {
    private int freeSessions;

    public PlatinumMember() {}

    public PlatinumMember(String memberID, double monthlyFee, int freeSessions) {
        super(memberID, monthlyFee);
        this.freeSessions = freeSessions;
    }

    @Override
    public double discountedFee() {
        if (monthlyFee > 8000) {
            return monthlyFee * 0.92;
        }
        return monthlyFee;
    }

    @Override
    public void showInfo() {
        System.out.println("Platinum Member ID: " + memberID);
        System.out.println("Monthly Fee: " + monthlyFee);
        System.out.println("Discounted Fee: " + discountedFee());
        System.out.println("Free Sessions: " + freeSessions);
        System.out.println("---------------------------");
    }
}

// StandardMember.java
class StandardMember extends Member implements IMemberOperation {
    private boolean groupClassAccess;

    public StandardMember() {}

    public StandardMember(String memberID, double monthlyFee, boolean groupClassAccess) {
        super(memberID, monthlyFee);
        this.groupClassAccess = groupClassAccess;
    }

    @Override
    public double discountedFee() {
        if (monthlyFee > 8000) {
            return monthlyFee * 0.92;
        }
        return monthlyFee;
    }

    @Override
    public void showInfo() {
        System.out.println("Standard Member ID: " + memberID);
        System.out.println("Monthly Fee: " + monthlyFee);
        System.out.println("Discounted Fee: " + discountedFee());
        System.out.println("Group Class Access: " + groupClassAccess);
        System.out.println("---------------------------");
    }
}

// Gym.java
class Gym {
    private String name;
    private Member mm[];

    public Gym() {}

    public Gym(String name, int count) {
        this.name = name;
        this.mm = new Member[count];
    }

    public void addMember(Member m) {
        for (int i = 0; i < mm.length; i++) {
            if (mm[i] == null) {
                mm[i] = m;
                System.out.println("Member " + m.memberID + " added to " + name);
                return;
            }
        }
        System.out.println("Gym is full!");
    }

    public void removeMember(String memberID) {
        for (int i = 0; i < mm.length; i++) {
            if (mm[i] != null && mm[i].memberID.equals(memberID)) {
                mm[i] = null;
                System.out.println("Member " + memberID + " removed.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void showMembers() {
        System.out.println("\n--- All Members in " + name + " ---");
        for (Member m : mm) {
            if (m != null) {
                m.showInfo();
            }
        }
    }

    public void totalRevenue() {
        double total = 0;
        for (Member m : mm) {
            if (m != null) {
                if (m instanceof IMemberOperation) {
                    total += ((IMemberOperation) m).discountedFee();
                } else {
                    total += m.monthlyFee;
                }
            }
        }
        System.out.println("Total Monthly Revenue for " + name + ": " + total);
    }
}

// Start.java (Driver Class)
public class Start {
    public static void main(String[] args) {
        // Polymorphic behavior: Creating Member references pointing to subclass objects
        Member m1 = new PlatinumMember("P-101", 9000.0, 5);
        Member m2 = new StandardMember("S-202", 5000.0, true);

        // Creating Gym object
        Gym fitZone = new Gym("FitZone Elite", 5);

        // Demonstrating relevant methods
        fitZone.addMember(m1);
        fitZone.addMember(m2);

        fitZone.showMembers();
        fitZone.totalRevenue();

        fitZone.removeMember("S-202");
        fitZone.showMembers();
    }
}