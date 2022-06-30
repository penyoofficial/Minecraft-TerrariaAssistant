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
 * 该类定义了友好的中文用户图形界面。
 * 
 * @author Penyo
 * @see GUI
 */
public class GUI_简体中文 {
    private Frame frame = new Frame("泰拉瑞亚有色标签生成器");
    private Panel op = new Panel(new FlowLayout());
    private Label templateLabel = new Label("模板：");
    private Choice templateChoice = new Choice();
    private Label typeLabel = new Label("类型：");
    private Choice typeChoice = new Choice();
    private TextArea io = new TextArea("在这里输入文本。");
    private Button generate = new Button("生成");

    public GUI_简体中文() {
        init();
        setLayout();
        registAction();
    }

    private void init() {
        templateChoice.add("无");
        templateChoice.add("<Boss的名字>已苏醒！");
        templateChoice.add("<玩家的名字>被杀死了。");
        templateChoice.add("<NPC的类型><NPC的名字>被杀死了...");
        templateChoice.add("<NPC的类型><NPC的名字>已到达！");
        templateChoice.add("好像<NPC的名字>正在举办派对");

        typeChoice.add("一般聊天");
        typeChoice.add("事件");
        typeChoice.add("战斗");
        typeChoice.add("玩家死亡");
        typeChoice.add("NPC死亡");
        typeChoice.add("NPC到达");
        typeChoice.add("一般状态讯息");
        typeChoice.add("派对");

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
                case "<Boss的名字>已苏醒！":
                    typeChoice.select("战斗");
                    break;
                case "<玩家的名字>被杀死了。":
                    typeChoice.select("玩家死亡");
                    break;
                case "<NPC的类型><NPC的名字>被杀死了...":
                    typeChoice.select("NPC死亡");
                    break;
                case "<NPC的类型><NPC的名字>已到达！":
                    typeChoice.select("NPC到达");
                    break;
                case "好像<NPC的名字>正在举办派对":
                    typeChoice.select("派对");
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
                    io.setText("标签不能嵌套！");
                    return;
                }

            String result = "[c/" + colorChange() + ":" + origin + "]";
            io.setText(result);
            try {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable trans = new StringSelection(result);
                clipboard.setContents(trans, null);
                frame.setTitle("泰拉瑞亚有色标签生成器：结果已经复制到剪贴板。");
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
            case "一般聊天":
                thisColor = "FFFFFF";
                break;
            case "事件":
                thisColor = "32FF82";
                break;
            case "战斗":
                thisColor = "AF4BFF";
                break;
            case "玩家死亡":
                thisColor = "E11919";
                break;
            case "NPC死亡":
                thisColor = "FF1919";
                break;
            case "NPC到达":
                thisColor = "327DFF";
                break;
            case "一般状态讯息":
                thisColor = "FFF014";
                break;
            case "派对":
                thisColor = "FF00A0";
                break;
        }
        io.setForeground(Color.decode("#" + thisColor));
        return thisColor;
    }
}