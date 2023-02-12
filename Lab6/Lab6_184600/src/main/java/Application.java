import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    public static Pair<String, BufferedImage> getPair(Path path){
        try{
            return Pair.of(path.getFileName().toString(), ImageIO.read(path.toFile()));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Pair<String, BufferedImage> processPair(Pair<String, BufferedImage> pair){
        BufferedImage original = pair.getRight();
        BufferedImage image = new BufferedImage(original.getWidth(),
                original.getHeight(),
                original.getType());
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int rgb = original.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                Color outColor = new Color(red, blue, green);
                int outRgb = outColor.getRGB();
                image.setRGB(i,j,outRgb);
            }
        }
        return Pair.of(pair.getLeft(), image);
    }

    public static void savePair(Pair<String, BufferedImage> pair, Path savePath){

        File outputfile = new File(savePath+"/" + pair.getLeft());
        try {
            ImageIO.write(pair.getRight(), "jpg", outputfile);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args){
        String original = args[0];
        String processed = args[1];


        long time=0;
        List<Path> files =null;
        Path source = Path.of(original);
        Path destination = Path.of(processed);
        try (Stream<Path> stream = Files.list(source)){
            files = stream.collect(Collectors.toList());
            time = System.currentTimeMillis();
            files.stream().map(Application::getPair).map(Application::processPair).forEach(pair -> savePair(pair, destination));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Czas 1: ");
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(3);
        try {
            List<Path> finalFiles = files;
            pool.submit(() -> {
                Stream<Path> stream = finalFiles.stream().parallel();
                stream.map(Application::getPair).map(Application::processPair).forEach(pair -> savePair(pair, destination));;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        pool.shutdown();
        System.out.println("Czas 3: ");
        System.out.println(System.currentTimeMillis() - time);


        time = System.currentTimeMillis();
        pool = new ForkJoinPool(5);
        try {
            List<Path> finalFiles = files;
            pool.submit(() -> {
                Stream<Path> stream = finalFiles.stream().parallel();
                stream.map(Application::getPair).map(Application::processPair).forEach(pair -> savePair(pair, destination));;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        pool.shutdown();
        System.out.println("Czas 5: ");
        System.out.println(System.currentTimeMillis() - time);
    }
}
