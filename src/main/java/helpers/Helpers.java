package helpers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {


    public List<String> getFileNames(String dirName) {

        List<String> fileNames = new ArrayList<>();

        try {

            Files.walk(Paths.get(dirName))
                    .filter(Files::isRegularFile)
                    .map(file -> file.getFileName().toString())
                    .forEach(fileName -> fileNames.add(dirName + fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNames;
    }


    public String createFileName(String url, String regex) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);

        m.find();

        return m.group();
    }


    public List<String> readImageUrlsFromFile(InputStream is) {

        InputStreamReader isr = new InputStreamReader(is);

        try (BufferedReader br = new BufferedReader(isr)){

            String line;
            List<String> urls = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                urls.add(line);
            }

            return urls;

        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file, ", ioe);
        }

    }


    public List<String> readUrlsFromFile(InputStream is) {

        InputStreamReader isr = new InputStreamReader(is);

        try (BufferedReader br = new BufferedReader(isr)){

            String line;
            List<String> urls = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                urls.add(line);
            }

            return urls;

        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file, ", ioe);
        }

    }


    public void writeToFile(String content, String fileName) {

        Path path = Path.of(fileName);

        try (PrintWriter pw = new PrintWriter(
                Files.newBufferedWriter(path,
                        StandardOpenOption.CREATE)
        )){

            pw.print(content);

        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot write file: " + fileName, ioe);
        }
    }


    public String getHtmlContent(String uri) {

        try {

            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {

                content.append(inputLine + "\n");
            }

            in.close();
            con.disconnect();

            return content.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public byte[] getImageFromUrl(String urlString) {

        try {

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            byte[] bytes = con.getInputStream().readAllBytes();

            con.disconnect();

            return bytes;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void writeImageToFile(String filename, byte[] bytes) {

        Path path = Path.of(filename);

        try {
            Files.write(path, bytes);


        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot write file: " + filename, ioe);
        }
    }


    // nem mindenhol van megadva minden adat, ezért kell
    public Number tryParseToNumber(String input) {

        try {

            if (input.contains(".")) {
                return Double.parseDouble(input);
            }

            return Integer.parseInt(input);

        } catch (NumberFormatException nfe) {

            return -1;
        }
    }


    public String parseData(String input, String regex) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        if (m.find()) {

            return m.group().equals("n") ? "N/A" : m.group();
        }

        return "N/A";
    }


    public String readDatasFromFile(String dir, String fileName) {

        Path path = Path.of(dir + fileName);
        String rawHtml = "error";

        try {

            rawHtml = Files.readString(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawHtml;
    }
}
