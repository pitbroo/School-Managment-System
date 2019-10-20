package LoginApp;

public enum option {
    Student, Admin;
    private option(){}
    public String value(){
        return name();
    }
    public static option formvalue(String v){
        return valueOf(v);
    }

}
