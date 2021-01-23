package fr.oxal.v2.utils.image;

import fr.oxal.v2.utils.text.TextUtils;
import fr.oxal.v2.utils.text.WavenParser;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;

import java.io.*;
import java.util.ArrayList;

public class ImageUtils {

    public static void factoringImage(String in, String out, Class<? extends NamedWavenEntity> c) {
        for (NamedWavenEntity e : WavenEntities.getAll(c, a -> a.isAvailable())) {

            File file = new File(out + c.getSimpleName().toLowerCase() + "/" + TextUtils.normalize(e.getParsedName(1, WavenParser.DELETE_CONDI)) + "/");
            file.mkdirs();
            for (File f : getImageOptional(in, e)) {
                file = new File(out + c.getSimpleName().toLowerCase() + "/" + TextUtils.normalize(e.getParsedName(1, WavenParser.DELETE_CONDI)) + "/" + f.getName());

                OutputStream fos = null;
                InputStream fis = null;
                try {
                    fos = new FileOutputStream(file);
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[1024];

                    while (fis.read(bytes) != -1) {
                        fos.write(bytes);
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        }
    }

    public static void factoringCheckImage(String in, String out, Class<? extends NamedWavenEntity> c) {
        for (NamedWavenEntity e : WavenEntities.getAll(c, a -> a.isAvailable())) {
            File file = new File(in + c.getSimpleName().toLowerCase() + "/" + TextUtils.normalize(e.getParsedName(1, WavenParser.DELETE_CONDI)) + "/");
            File f = new File(out + c.getSimpleName().toLowerCase() + "/");
            f.mkdirs();
            if (file.exists()){
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files.length == 1) {
                        String name = out + c.getSimpleName().toLowerCase() + "/" + e.getId() + "-logo.png";
                        if (e instanceof WithSkin){
                            //name = out + c.getSimpleName().toLowerCase() + "/" + e.getId() + "#" + ((WithSkin) e).getDefaultSkin().get() + ".png";
                        }
                        f = new File(name);
                        OutputStream fos = null;
                        InputStream fis = null;
                        try {
                            fos = new FileOutputStream(f);
                            fis = new FileInputStream(files[0]);
                            byte[] bytes = new byte[1024];

                            while (fis.read(bytes) != -1) {
                                fos.write(bytes);
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {

                    }
                }
            }else{
            }

        }
    }

    public static ArrayList<File> getImageOptional(String in, WavenInterface w) {

        ArrayList<File> list = new ArrayList<>();
        File file = new File(in);

        boolean check = true;
        try {
            for (File f : file.listFiles()) {
                if (f.getName().contains(TextUtils.normalize(w.asNamedEntity().getFileNameConsitution()))) {
                    list = new ArrayList<>();
                    list.add(f);
                    check = false;
                } else if (check && f.getName().contains(TextUtils.normalize(w.asNamedEntity().getName().split(" ")[w.asNamedEntity().getName().split(" ").length - 1]))) {
                    list.add(f);
                } else if (check && f.getName().contains(TextUtils.normalize(w.asNamedEntity().getName().split(" ")[0]))) {
                    list.add(f);
                } else {

                }
            }
        } catch (Exception e) {

        }

        if (list.isEmpty()) {

        }

        return list;
    }
}
