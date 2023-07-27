import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListSerialization {

    public void serialize(UserType userType, List list) throws IOException {
        OutputStream os = new FileOutputStream("list.xml");
        OutputStreamWriter osr = new OutputStreamWriter(os);
        Writer w = osr;
        w.write("<list size=\""+list.getSize()+"\">\n");
        list.forEach(f -> {userType.writeValue(osr, f);});
        w.write("</list>");
        w.close();
        osr.close();
        os.close();
    }

    public List deserialize(UserType userType, List l) throws IOException {
        InputStream os = new FileInputStream("list.xml");
        InputStreamReader osr = new InputStreamReader(os);
        Reader r = osr;
        int data = r.read();
        boolean checkSize = false;
        String line = "";
        while (data != -1) {
            if((char)data == '\n') {
                break;
            }
            line += (char)data;
            data = r.read();
        }
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        int size = 0;
        while(m.find()) {
            size = Integer.parseInt(m.group());
        }

        for(int i = 0; i < size; i++) {
            Object a = userType.readValue(osr);
            l.push(a);
        }

        r.close();
        osr.close();
        os.close();
        return l;
    }
}
