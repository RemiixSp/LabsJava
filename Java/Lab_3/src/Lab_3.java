import java.io.*;
import java.util.*;
import java.util.List;

class Lab_3 {
    final static String filePath1 = "E:/write.txt";
    final static String filePath2 = "E:/write_2.txt";

    public static void main(String[] args)
    {
        System.out.println("Open file : 1 ; 2 ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.next();
        switch (filename){
            case "1":
                filename=filePath1;
                break;
            case "2":
                filename=filePath2;
                break;
            default:
                return;
        }
        Map<String, Set<String>> mapFromFile = GetFromTextFile(filename);
        Map<String, Set<String>> names = GetSameFilms(filename);
        for (Map.Entry<String, Set<String>> entry :
                mapFromFile.entrySet()) {
            System.out.println(entry.getKey() + " : "
                    + entry.getValue());
        }
        System.out.println("\nFilms with the same date\n");
        for (Map.Entry<String, Set<String>> entry :
                names.entrySet()) {
            System.out.println(entry.getKey() + " : "
                    + entry.getValue());
        }
    }
    public static List<String> GetFilmName(String filename){
        List<String> film= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                if (!name.equals("")) film.add(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return film;
    }
    public static List<String> GetDate(String filename){
        List<String> date= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[1].trim();
                if (!name.equals("")) date.add(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return date;
    }
    public static List<String> GetDirector(String filename){
        List<String> Director= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[2].trim();
                if (!name.equals("")) Director.add(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return Director;
    }
    public static List<String> GetProdusers(String filename){
        List<String> Produsers= new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[3].trim();
                if (!name.equals("")) Produsers.add(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
        return Produsers;
    }
    public static Map<String, Set<String>> GetFromTextFile(String filename)
    {
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
        List<String> film=Lab_3.GetFilmName(filename);
        List<String> produser = Lab_3.getListOfProdusers(filename);
        List<String> date = Lab_3.GetDate(filename);
        List<String> director= Lab_3.GetDirector(filename);
        for (int i = 0; i < produser.size(); i++) {
            Set<String> temp = new TreeSet<>();
            for (int j = 0; j < film.size(); j++) {
             if(Lab_3.GetProdusers(filename).get(j).contains(produser.get(i))){
                 temp.add(film.get(j)+" "+ date.get(j)+" "+director.get(j));
             }
            }
            map.put(produser.get(i),temp);
        }
        return map;
    }
    public static Map<String,Set<String>> GetSameFilms(String filename){
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
        List<String> film=Lab_3.GetFilmName(filename);
        List<String> date = new ArrayList<>();
        date.addAll(Lab_3.getListOfDate(filename));
        for (int i = 0; i < date.size(); i++) {
            Set<String> temp = new TreeSet<>();
            for (int j = 0; j < film.size(); j++) {
                if(Lab_3.GetDate(filename).get(j).contains(date.get(i))){
                    temp.add(film.get(j));
                }
            }
            map.put(date.get(i),temp);
        }
        return map;
    }
    public static List<String> getListOfProdusers(String filename){
        String prod= "";
        for(int i =0;i<Lab_3.GetProdusers(filename).size()-1;i++){
            prod += Lab_3.GetProdusers(filename).get(i)+" ";
        }
        prod+=Lab_3.GetProdusers(filename).get(Lab_3.GetProdusers(filename).size()-1);
        String prodq[]= prod.split(" ");
        List<String> produser =new ArrayList<>();
        produser = Arrays.asList(prodq);
        Set<String> pr= new TreeSet<>(produser);
        List<String> produser1 =new ArrayList<>();
        produser1.addAll(pr);
        return produser1;
    }
    public static Set<String> getListOfDate(String filename){
        List<String> Date =new ArrayList<>();
        Date = Lab_3.GetDate(filename);
        Set<String> dat= new TreeSet<>(Date);
        return dat;
    }
}