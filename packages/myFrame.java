package packages;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.time.LocalDate;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class myFrame extends JFrame{

    private JMenuBar options;
    private JMenu filemenu;
    private JMenu savemenu;
    private JMenu deletemenu;
    private JMenu information;
    private JPanel sliders;
    private JPanel mainpanel;

    private JSlider slider1;
    private JPanel slider1panel;
    private JLabel slider1label;

    private JSlider slider2;
    private JPanel slider2panel;
    private JLabel slider2label;

    private JSlider slider3;
    private JPanel slider3panel;
    private JLabel slider3label;

    private JLabel currentobject;
    private JLabel currentcolor;

    private JButton colorpick;

    private Color mugc = Color.red;
    private Color mughac = Color.black;
    private Color mughoc = Color.gray;
    private Color c;


    private Rectangle rect;
    private Rectangle mug1hold;
    private Rectangle mug1hole;

    private Rectangle mug2;
    private Rectangle mug2hold;
    private Rectangle mug2hole;

    private Rectangle mug3;
    private Rectangle mug3hold;

    private JLabel filename;
    private JLabel currdate;
    private JLabel desc;
    private JLabel mugsize;
    private JLabel mugarea;
    private JLabel mugvolume;

    private Random random;

    private Mug mug;

    public myFrame(){

        ImageIcon image = new ImageIcon("src/packages/muggicon.PNG");
        this.setIconImage(image.getImage());

        random = new Random();

        int rndx = random.nextInt(76) + 25;
        int rndy = random.nextInt(76) + 25;
        int rndz = random.nextInt(76) + 25;

        Color rndcmug = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        Color rndcmughold = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        Color rndcmughole = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));

        mugc = rndcmug;
        mughac = rndcmughold;
        mughoc = rndcmughole;

        options = new JMenuBar();
        options.setBounds(0,0,900,50);

        filemenu = new JMenu("Delete");
        savemenu = new JMenu("Save");
        deletemenu = new JMenu("Load");
        information = new JMenu("Info");

        filemenu.addMenuListener(new FileMenuListener());
        savemenu.addMenuListener(new SaveMenuListener());
        deletemenu.addMenuListener(new DeleteMenuListener());
        information.addMenuListener(new InfoMenuListener());
    
        options.add(filemenu);
        options.add(savemenu);
        options.add(deletemenu);
        options.add(information);

        sliders = new JPanel();
        sliders.setBounds(0,60,200,330);
        sliders.setLayout(new GridLayout(1,3));

        slider1panel = new JPanel();
        slider1panel.setLayout(new BorderLayout());

        slider1 = new JSlider(25,100,rndx);
        slider1.setPaintTicks(true);
        slider1.setMinorTickSpacing(5);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(25);
        slider1.setOrientation(SwingConstants.VERTICAL);
        slider1.setPreferredSize(new Dimension(100,300));

        slider1label = new JLabel();
        slider1label.setText(slider1.getValue() + "mm");
        slider1label.setHorizontalAlignment(JLabel.CENTER);

        slider1panel.add(slider1, BorderLayout.PAGE_START);
        slider1panel.add(slider1label, BorderLayout.CENTER);

        slider2panel = new JPanel();
        slider2panel.setLayout(new BorderLayout());

        slider2 = new JSlider(25,100,rndy);
        slider2.setPaintTicks(true);
        slider2.setMinorTickSpacing(10);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(25);
        slider2.setOrientation(SwingConstants.VERTICAL);
        slider2.setPreferredSize(new Dimension(100,300));

        slider2label = new JLabel();
        slider2label.setText(slider2.getValue() + "mm");
        slider2label.setHorizontalAlignment(JLabel.CENTER);

        slider2panel.add(slider2, BorderLayout.PAGE_START);
        slider2panel.add(slider2label, BorderLayout.CENTER);

        slider3panel = new JPanel();
        slider3panel.setLayout(new BorderLayout());

        slider3 = new JSlider(25,100,rndz);
        slider3.setPaintTicks(true);
        slider3.setMinorTickSpacing(10);
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(25);
        slider3.setOrientation(SwingConstants.VERTICAL);
        slider3.setPreferredSize(new Dimension(100,300));

        slider3label = new JLabel();
        slider3label.setText(slider3.getValue() + "mm");
        slider3label.setHorizontalAlignment(JLabel.CENTER);

        slider3panel.add(slider3, BorderLayout.PAGE_START);
        slider3panel.add(slider3label, BorderLayout.CENTER);

        colorpick = new JButton("Pick a color!");
        colorpick.setBounds(10, 500, 180, 50);

        currentobject = new JLabel();
        currentobject.setBounds(10, 420,180,20);
        currentobject.setText("Object selected: None");

        currentcolor = new JLabel();
        currentcolor.setBounds(10, 450,180,20);
        currentcolor.setText("Color selected: None");

        colorpick.addActionListener(new colorlistener());
        slider1.addChangeListener(new sl1listener());
        slider2.addChangeListener(new sl2listener());
        slider3.addChangeListener(new sl3listener());


        sliders.add(slider1panel);
        sliders.add(slider2panel);
        sliders.add(slider3panel);

        filename = new JLabel();
        filename.setText("Current file: None");
        filename.setBounds(590, 280, 150,100);

        currdate = new JLabel();
        LocalDate locdate = LocalDate.now();
        currdate.setText("Date: " + locdate);
        currdate.setBounds(590,310,300,100);

        desc = new JLabel();
        desc.setText("A bögre tulajdonságai:");
        desc.setBounds(590, 340, 150, 100);

        mug = new Mug(rndz, rndx, rndy, mugc, mughoc, mughac);

        rect = new Rectangle(30,30,mug.getWidth_x() + 70,mug.getHeight() + 70);
        mug1hold = new Rectangle(mug.getWidth_x() + 100,60,40,mug.getHeight() + 20);
        mug1hole = new Rectangle(mug.getWidth_x() + 100, 75 , 25 , mug.getHeight() - 10);
        mug2 = new Rectangle(400, 30, mug.getWidth_x() + 70, mug.getWidth_y() + 70);
        mug2hold = new Rectangle(400 + mug.getWidth_x() + 70, (mug.getWidth_y()+70)/2 + 15, 40 , 30);
        mug2hole = new Rectangle(410, 40, mug.getWidth_x() + 50, mug.getWidth_y() + 50);
        mug3 = new Rectangle(30, 270, mug.getWidth_y()+70, mug.getHeight() + 70);
        mug3hold = new Rectangle((mug.getWidth_y() +70)/2 + 15, 300, 30, mug.getHeight() + 20);

        mainpanel = new JPanel() {
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(mugc);
                g2.fill(rect);
                g2.setColor(mughac);
                g2.fill(mug1hold);
                g2.setColor(Color.white);
                g2.fill(mug1hole);
                g2.setColor(mugc);
                g2.fillOval(400, 30, mug.getWidth_x() + 70, mug.getWidth_y() + 70);
                g2.setColor(mughoc);
                g2.fillOval(410, 40, mug.getWidth_x() + 50, mug.getWidth_y() + 50);
                g2.setColor(mughac);
                g2.fill(mug2hold);
                g2.setColor(mugc);
                g2.fill(mug3);
                g2.setColor(mughac);
                g2.fill(mug3hold);
                g2.setColor(Color.gray);
                g2.drawLine(0, 0, 0, 700);
                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(rect.x, rect.y, rect.width, rect.height);
                g2.drawRect(mug1hole.x, mug1hole.y, mug1hole.width, mug1hole.height);
                g2.drawRect(mug1hold.x, mug1hold.y, mug1hold.width, mug1hold.height);
                g2.drawOval(mug2.x, mug2.y, mug2.width, mug2.height);
                g2.drawRect(mug2hold.x, mug2hold.y, mug2hold.width, mug2hold.height);
                g2.drawOval(mug2hole.x, mug2hole.y, mug2hole.width, mug2hole.height);
                g2.drawRect(mug3.x, mug3.y, mug3.width, mug3.height);
                g2.drawRect(mug3hold.x, mug3hold.y, mug3hold.width, mug3hold.height);
            }
        };
        mainpanel.addMouseListener(new rectmouselistener());
        mainpanel.setBounds(200,50,800,550);

        mugsize = new JLabel();
        mugsize.setText("-  Méret: "+ slider1.getValue() +"mm x "+ slider2.getValue() +"mm x "+ slider3.getValue() + "mm");
        mugsize.setBounds(590,370,250,100);

        mugarea = new JLabel();
        mugarea.setText("-  Kerület: " + String.format("%.2f",(1 * ((float)slider1.getValue()/10) * ((float)slider2.getValue())/10) + (2* ((float)slider1.getValue()/10) * ((float)slider3.getValue())/10) + (2 * ((float)slider3.getValue()/10) * ((float)slider2.getValue()/10))) + "cm^2");
        mugarea.setBounds(590,400,250,100);

        mugvolume = new JLabel();
        mugvolume.setText("-  Térfogat: " + String.format("%.2f",((float)slider1.getValue()/10) * ((float)slider2.getValue()/10) * ((float)slider3.getValue()/10)) + "cm^3");
        mugvolume.setBounds(590,430,150,100);

        try {
            File dir = new File("mugdirectory");
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception a) {
            System.out.println("Something went wrong");
        }


        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850,600);
        this.setTitle("Bögre");
        this.setResizable(false);
        this.add(currdate);
        this.add(filename);
        this.add(desc);
        this.add(mugsize);
        this.add(mugarea);
        this.add(mugvolume);

        this.add(sliders);
        this.add(options);
        this.add(colorpick);
        this.add(currentobject);
        this.add(currentcolor);
        this.add(mainpanel);

    }

    public void changevalues() {
        mugsize.setText("-  Méret: "+ slider1.getValue() +"mm x "+ slider2.getValue() +"mm x "+ slider3.getValue() + "mm");
        mugarea.setText("-  Kerület: " + String.format("%.2f",(1 * ((float)slider1.getValue()/10) * ((float)slider2.getValue())/10) + (2* ((float)slider1.getValue()/10) * ((float)slider3.getValue())/10) + (2 * ((float)slider3.getValue()/10) * ((float)slider2.getValue()/10))) + "cm^2");
        mugvolume.setText("-  Térfogat: " + String.format("%.2f",((float)slider1.getValue()/10) * ((float)slider2.getValue()/10) * ((float)slider3.getValue()/10)) + "cm^3");
    }

    class colorlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JColorChooser colorchooser = new JColorChooser();
            c = JColorChooser.showDialog(null, "Pick a color", Color.black);

            currentcolor.setText("Color selected: Selected");
            currentcolor.setForeground(c);
        }

    }

    class sl1listener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            slider1label.setText(slider1.getValue() + "mm");
            rect.width = (slider1.getValue() + 70);
            mug1hold.x = rect.width + rect.x;
            mug1hole.x = mug1hold.x;
            mug2.width = rect.width;
            mug2hole.width = rect.width - 20;
            mug2hold.x = mug2.x + mug2.width;
            repaint();
            changevalues();
            mug.setWidth_x(slider1.getValue());
        }

    }

    class sl2listener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            slider2label.setText(slider2.getValue() + "mm");
            mug2.height = slider2.getValue() + 70;
            mug2hole.height = mug2.height - 20;
            mug2hold.y = mug2.height / 2 + 15;
            mug3.width = mug2.height;
            mug3hold.width = mug2hold.height;
            mug3hold.x = mug3.width / 2 + 15;
            repaint();
            changevalues();
            mug.setWidth_y(slider2.getValue());
        }

    }

    class sl3listener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            slider3label.setText(slider3.getValue() + "mm");
            rect.height = slider3.getValue() + 70;
            mug1hold.height = slider3.getValue() + 20;
            mug1hole.height = slider3.getValue() - 10;
            mug3.height = rect.height;
            mug3hold.height = mug1hold.height;
            repaint();
            changevalues();
            mug.setHeight(slider3.getValue());
        }

    }

    class rectmouselistener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            if(rect.contains(e.getX(), e.getY()) || (mug2.contains(e.getX(), e.getY()) && !mug2hole.contains(e.getX(), e.getY())) || (mug3.contains(e.getX(), e.getY()) && !mug3hold.contains(e.getX(),e.getY()))) {
                currentobject.setText("Object selected: Mug");
                if(currentcolor.getText() != "Color selected: None") {
                    mugc = c;
                    mug.setMug_color(c);
                }
            }
            else if(mug2hold.contains(e.getX(), e.getY()) || (!mug1hole.contains(e.getX(),e.getY()) && mug1hold.contains(e.getX(),e.getY())) || mug3hold.contains(e.getX(), e.getY())) {
                currentobject.setText("Object selected: Handle");
                if(currentcolor.getText() != "Color selected: None") {
                    mughac = c;
                    mug.setMug_handle_color(c);
                }
            }
            else if(mug2hole.contains(e.getX(), e.getY())) {
                currentobject.setText("Object selected: Mug inside");
                if(currentcolor.getText() != "Color selected: None") {
                    mughoc = c;
                    mug.setMug_hole_color(c);
                }
            }
            else {
                currentobject.setText("Object selected: None");
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    class FileMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("File selected");
            String input = JOptionPane.showInputDialog(null, "Filename: ", "Delete file", JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.isEmpty()) {
                input = "mugdirectory/" + input + ".txt";
                try {
                    File file = new File(input);
                    if (file.exists()) {
                        String sure = JOptionPane.showInputDialog(null, "Do you want to delete this file? " + input + " y/n","Delete file",JOptionPane.QUESTION_MESSAGE);
                        if (sure.equals("y")) {
                            file.delete();
                            JOptionPane.showMessageDialog(null, "File deleted!","Delete file",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "File was not deleted!","Information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong filename!","Error",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception a) {
                    System.out.println("Something went wrong");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No filename was selected!","Error",JOptionPane.INFORMATION_MESSAGE);
            }

        }

        @Override
        public void menuDeselected(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("File deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("File canceled");
        }

    }

    class SaveMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            // TODO Auto-generated method stub
            String input = JOptionPane.showInputDialog(null, "Filename: ", "Save file", JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.isEmpty()) {
                filename.setText("Current file: " + input);
                input = "mugdirectory/" + input + ".txt";
                mug.setDate(LocalDate.now());
                JOptionPane.showMessageDialog(null,"Saved succesfully!" , "Message",JOptionPane.INFORMATION_MESSAGE);
                mug.setName(input);
                mug.saveMug(input);
                currdate.setText("File saved: " + mug.getDate());

            } else {
                JOptionPane.showMessageDialog(null, "No filename was selected!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Save deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Save canceled");
        }

    }

    class DeleteMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            // TODO Auto-generated method stub
            String input = JOptionPane.showInputDialog(null, "Filename: ", "Load file",JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.isEmpty()) {
                try {
                    File file = new File("mugdirectory/" + input + ".txt");
                    if (file.exists()) {
                        input = "mugdirectory/" + input + ".txt";
                        JOptionPane.showMessageDialog(null,"Loaded succesfully!" , "Message",JOptionPane.INFORMATION_MESSAGE);
                        mug = Mug.loadMug(input);
                        slider1.setValue(mug.getWidth_x());
                        slider1label.setText(mug.getWidth_x() + "mm");
                        slider2.setValue(mug.getWidth_y());
                        slider2label.setText(mug.getWidth_y() + "mm");
                        slider3.setValue(mug.getHeight());
                        slider3label.setText(mug.getHeight() + "mm");

                        mugc = mug.getMug_color();
                        mughac = mug.getMug_handle_color();
                        mughoc = mug.getMug_hole_color();
                        filename.setText("Current file: " + mug.getName().substring(13,mug.getName().length() - 4));
                        currdate.setText("File saved: " + mug.getDate());
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null,"File doesn't exists!" , "Error",JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception a) {
                    System.out.println("Something went wrong");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No filename was selected!","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Delete deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Delete canceled");
        }

    }

    class InfoMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            // TODO Auto-generated method stub
            JOptionPane.showMessageDialog(null, "A Bögre app!\nKészítette: Dóka Róbert\nNeptun: CGITAR\n\nA program képes 3 különböző nézőpontot bemutatni. Csúszkák segítségével\n a bögrét lehet méretezni. A színválasztóval pedig a kijelökt bögre részt lehet színezni.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Info deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            // TODO Auto-generated method stub

        }

    }


}

