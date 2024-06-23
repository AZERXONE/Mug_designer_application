package packages;
import java.awt.Color;
import java.io.*;
import java.time.LocalDate;

public class Mug implements Serializable {

    private int height;
    private int width_x;
    private int width_y;

    private Color mug_color;
    private Color mug_hole_color;
    private Color mug_handle_color;

    private String name = "None";
    private LocalDate date;

    public Mug(int height, int width_x, int width_y, Color mug_color, Color mug_hole_color, Color mug_handle_color) {

        this.height = height;
        this.width_x = width_x;
        this.width_y = width_y;

        this.mug_color = mug_color;
        this.mug_handle_color = mug_handle_color;
        this.mug_hole_color = mug_hole_color;


    }

    public void saveMug(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            os.close();
            fos.close();


        } catch (Exception e) {
            System.out.println("Something went wrong");
        }


    }

    public static Mug loadMug(String filename) {

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            Object obj = is.readObject();
            is.close();
            fis.close();
            return (Mug) obj;

        } catch (Exception e) {
            System.out.println("Something went wrong");
            return null;
        }

    }

    public void getData() {
        System.out.println("Height: " + this.height);
        System.out.println("Width x: " + this.width_x);
        System.out.println("Width y:" + this.width_y);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth_x() {
        return width_x;
    }

    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }

    public int getWidth_y() {
        return width_y;
    }

    public void setWidth_y(int width_y) {
        this.width_y = width_y;
    }

    public Color getMug_color() {
        return mug_color;
    }

    public void setMug_color(Color mug_color) {
        this.mug_color = mug_color;
    }

    public Color getMug_hole_color() {
        return mug_hole_color;
    }

    public void setMug_hole_color(Color mug_hole_color) {
        this.mug_hole_color = mug_hole_color;
    }

    public Color getMug_handle_color() {
        return mug_handle_color;
    }

    public void setMug_handle_color(Color mug_handle_color) {
        this.mug_handle_color = mug_handle_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
