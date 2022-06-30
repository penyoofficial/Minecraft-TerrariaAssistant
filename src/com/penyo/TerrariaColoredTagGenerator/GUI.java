package com.penyo.TerrariaColoredTagGenerator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 该类定义了友好的用户图形界面。
 * 
 * @author Penyo
 */
public class GUI {
    private Frame frame = new Frame("Terraria Colored Tag Generator");
    private Panel op = new Panel(new FlowLayout());
    private Label templateLabel = new Label("Template:");
    private Choice templateChoice = new Choice();
    private Label typeLabel = new Label("Type:");
    private Choice typeChoice = new Choice();
    private TextArea io = new TextArea("Input something here.");
    private Button generate = new Button("Generate");

    public GUI() {
        init();
        setLayout();
        registAction();
    }

    private void init() {
        templateChoice.add("None");
        templateChoice.add("<Boss' name> has awoken!");
        templateChoice.add("<Player name> was slain.");
        templateChoice.add("<Name of NPC> the <type of NPC> was slain...");
        templateChoice.add("<Name of NPC> the <type of NPC> has arrived!");
        templateChoice.add("Looks like <NPC name> is throwing a party");

        typeChoice.add("Average chat");
        typeChoice.add("Event");
        typeChoice.add("Fight");
        typeChoice.add("Player's death");
        typeChoice.add("NPC's death");
        typeChoice.add("NPC's arrival");
        typeChoice.add("Average status msg");
        typeChoice.add("Party");

        op.add(templateLabel);
        op.add(templateChoice);
        op.add(typeLabel);
        op.add(typeChoice);
        op.add(generate);

        frame.add(op, BorderLayout.NORTH);
        frame.add(io);
    }

    private void setLayout() {
        io.setBackground(Color.BLACK);
        io.setFont(new Font(null, Font.ITALIC, 15));
        io.setForeground(Color.WHITE);

        frame.setSize(640, 320);
        frame.setVisible(true);
    }

    private void registAction() {
        io.addTextListener(e -> io.setFont(new Font(null, Font.BOLD, 15)));
        templateChoice.addItemListener(e -> {
            switch (templateChoice.getSelectedItem()) {
                case "<Boss' name> has awoken!":
                    typeChoice.select("Fight");
                    break;
                case "<Player name> was slain.":
                    typeChoice.select("Player's death");
                    break;
                case "<Name of NPC> the <type of NPC> was slain...":
                    typeChoice.select("NPC's death");
                    break;
                case "<Name of NPC> the <type of NPC> has arrived!":
                    typeChoice.select("NPC's arrival");
                    break;
                case "Looks like <NPC name> is throwing a party":
                    typeChoice.select("Party");
                    break;
                default:
                    return;
            }
            io.setText(templateChoice.getSelectedItem());
            colorChange();
        });
        typeChoice.addItemListener(e -> colorChange());
        generate.addActionListener(e -> {
            String origin = io.getText();
            for (int i = 0; i < io.getText().length(); i++)
                if (origin.charAt(i) == '[' || origin.charAt(i) == ']') {
                    io.setText("Tag cannot be nested!");
                    return;
                }

            String result = "[c/" + colorChange() + ":" + origin + "]";
            io.setText(result);
            try {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable trans = new StringSelection(result);
                clipboard.setContents(trans, null);
                frame.setTitle("Terraria Colored Tag Generator: The result has been copied to the clipboard.");
            } catch (Exception e1) {
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private String colorChange() {
        String thisColor = "FFFFFF";
        switch (typeChoice.getSelectedItem()) {
            case "Average chat":
                thisColor = "FFFFFF";
                break;
            case "Event":
                thisColor = "32FF82";
                break;
            case "Fight":
                thisColor = "AF4BFF";
                break;
            case "Player's death":
                thisColor = "E11919";
                break;
            case "NPC's death":
                thisColor = "FF1919";
                break;
            case "NPC's arrival":
                thisColor = "327DFF";
                break;
            case "Average status msg":
                thisColor = "FFF014";
                break;
            case "Party":
                thisColor = "FF00A0";
                break;
        }
        io.setForeground(Color.decode("#" + thisColor));
        return thisColor;
    }
}