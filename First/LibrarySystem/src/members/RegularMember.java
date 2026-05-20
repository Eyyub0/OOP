package members;

public class RegularMember extends Member {

    public RegularMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxItems() { return 3; }

    @Override
    public String getMemberType() { return "Regular"; }
}