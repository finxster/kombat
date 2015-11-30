package io.kombat.core.utils;

import org.apache.commons.io.IOUtils;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
@Singleton
public class FileManager implements Serializable {

    private static final long serialVersionUID = -7239007773173467261L;

    private static final String UPLOAD_PATH = "./src/main/webapp/uploads";

    private File createFile(String path) throws IOException {
        Pattern p = Pattern.compile("(.*\\/)([^\\.\\/]+\\.[\\d\\w]+)$");
        Matcher matcher = p.matcher(path);
        if (matcher.find()) {
            File dir = new File(matcher.group(1));
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            return file;
        }

        return null;
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    public String create(HttpServletRequest request, String field, String path) {
        try {
            Part upload = request.getPart(field);
            if (upload == null || 0L == upload.getSize()) {
                return null;
            }

            String fileName = getFileName(upload);
            InputStream in = upload.getInputStream();
            String relativeFilePath = path + "/" + fileName;

            File file = createFile(UPLOAD_PATH + relativeFilePath);
            if (file == null) {
                return null;
            }

            FileOutputStream out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            in.close();
            out.close();

            return relativeFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return null;
    }
}
