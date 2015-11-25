package io.kombat.core.servlet;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ac-bsilva on 23/11/15.
 */
public class HttpRequest extends HttpServletRequestWrapper {

    private static final String METHOD_OVERRIDE_PARAM = "_method";

    private final String method;
    private final Map<String, Object> params;

    public HttpRequest(HttpServletRequest request) throws IOException {
        super(request);
        String method = request.getMethod();
        Map<String, Object> params = new LinkedHashMap<String, Object>();

        Pattern field = Pattern.compile("([^=]+)=(.*)");
        Matcher matcher;
        String chunkString;
        ServletInputStream in = request.getInputStream();
        Scanner scanner = new Scanner(in).useDelimiter("&");

        while (scanner.hasNext()) {
            chunkString = URLDecoder.decode(scanner.next(), "UTF-8");
            matcher = field.matcher(chunkString);

            if (matcher.find()) {
                if (METHOD_OVERRIDE_PARAM.equals(matcher.group(1))) {
                    method = matcher.group(2);
                    continue;
                }

                params.put(matcher.group(1), matcher.group(2));
            }
        }

        this.params = params;
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

}
