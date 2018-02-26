package dangod.themis.util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class CodeGenerate {
    public static void Generrate(String rootPath, String templateFile, String target, Map<String, String> model) {
        Configuration cfg = new Configuration(Configuration.getVersion());
        Template template = null;
        Writer out = null;
        try {
            cfg.setDirectoryForTemplateLoading(new File(rootPath));
            template = cfg.getTemplate(templateFile);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "UTF-8"));
            template.process(model, out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
