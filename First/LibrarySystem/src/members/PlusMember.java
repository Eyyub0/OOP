package members;

public class PlusMember extends Member {

    public PlusMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxItems() { return 6; }

    @Override
    public String getMemberType() { return "Plus"; }
}