package members;

public class EliteMember extends Member {

    public EliteMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxItems() { return 9; }

    @Override
    public String getMemberType() { return "Elite"; }
}