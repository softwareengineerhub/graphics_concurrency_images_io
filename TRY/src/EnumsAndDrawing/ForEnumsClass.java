package EnumsAndDrawing;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public enum ForEnumsClass {
    LINE("������"),
    PARAB("������ ������"),
    CUBE("���������");

    String functionTitle;
    private ForEnumsClass(String functionTitle) {
        this.functionTitle=functionTitle;
    }

    public String getFunctionTitle(){
        return functionTitle;
    }


}
