/*
 * Created by JFormDesigner on Sat Apr 04 09:45:37 CST 2020
 */

package All_Tool_List.Tool.NetTool.WebDirectoryScan;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

/**
 * @author 1
 */
public class WebDirectoryScan_form extends JPanel {
    public WebDirectoryScan_form() {
        initComponents();
    }

    public WebDirectoryScan_impl webDirectoryScan_impl = new WebDirectoryScan_impl();
    public static WDS_Bean bean = new WDS_Bean();

    private void DictionarySelectBTActionPerformed(ActionEvent e) {
        bean.setDirectoryFile(webDirectoryScan_impl.DirectoryGet());//获取字典文件
        WebDirectoryScanURLArea2.setText(WebDirectoryScanURLTF.getText().replace("{dir}",bean.getDirectoryText().get(0)));//设置预览待访问URL
        DirectiryNumberTF.setText(String.valueOf(bean.getDirectoryTextNum()));//显示字典条目数
        WebDirectoryScanDictionaryPathTF.setText(bean.getDirectoryFile().getPath());//显示字典路径
    }

    private void StartBTActionPerformed(ActionEvent e) {
        StartBT.setEnabled(false);
        //CanelBT.setVisible(true);
        RunTimeTF.setText("Running");
        this.StartInit();//启动前的初始设置与执行线程池
    }

    private void StartInit(){
        WebDirectoryScanOutArea.setText("");
        PB.setMaximum(bean.getPBMaxNum());//ProgressBar最大值
        PB.setMinimum(bean.getPBMinNum());//ProgressBar最小值
        bean.setThreadNum((int)TCSP.getValue());
        bean.setTimeOut((int)DelaySP.getValue());
        bean.setURL(WebDirectoryScanURLTF.getText());
        bean.setMatchNumList(webDirectoryScan_impl.MatchNumListGet(WebDirectoryScanStrusCodeArea));//设置要匹配的状态码

        for (String a:bean.getMatchNumList()) {
            WDSMatchNumCB.addItem(a);
        }

        //SwingWorker
        new WDSSwingWorker(bean,WebDirectoryScanOutArea,PB,StartBT,CanelBT,RunTimeTF).execute();
    }

    private void WebDirectoryScanURLArea2CaretUpdate(CaretEvent e) {
        if(WebDirectoryScanURLArea2.getText()!="")
        {
            StartBT.setEnabled(true);
        }
    }

    private void WebDirectoryScanURLTFCaretUpdate(CaretEvent e) {
        if(WebDirectoryScanDictionaryPathTF.getText().length()!=0){
            WebDirectoryScanURLArea2.setText(WebDirectoryScanURLTF.getText().replace("{dir}",bean.getDirectoryText().get(0)));
        }
    }

    private void CanelBTActionPerformed(ActionEvent e) {
        //StartBT.setEnabled(true);
        //CanelBT.setVisible(false);
        //RunTimeTF.setText("Waiting");
        //WDSS.stop();
        //System.gc();
    }

    private void WDSMatchNumCBItemStateChanged(ItemEvent e) {
        WebDirectoryScanOutArea.setText("");
        for (String a:bean.getResultOut().split("\n")) {
            if(a.substring(0,3).equals(WDSMatchNumCB.getSelectedItem().toString()))
            {
                WebDirectoryScanOutArea.append(a+"\n");
            }else if(WDSMatchNumCB.getSelectedItem().toString().equals("ALL")&&WDSMatchNumCB.getSelectedItem().toString()!="null")
            {
                WebDirectoryScanOutArea.append(a+"\n");
            }
            //System.out.println(a.substring(0,3));
        }
    }

    private void TextOutBTActionPerformed(ActionEvent e) {
        saveToFile();
        /**
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showDialog(new JLabel(),"选择文件夹");

        if(chooser.getSelectedFile().isFile()&&bean.getResultOut()!=""){
            File writename = chooser.getSelectedFile();
            try {
                writename.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                for (String a:bean.getResultOut().split("\n")) {
                    if(a.substring(0,3).equals(WDSMatchNumCB.getSelectedItem().toString()))
                    {
                        out.write(a+"\r\n");
                    }else if(WDSMatchNumCB.getSelectedItem().toString().equals("ALL")&&WDSMatchNumCB.getSelectedItem().toString()!="null")
                    {
                        out.write(a+"\r\n");
                    }
                    out.flush(); // 把缓存区内容压入文件
                    out.close(); // 最后记得关闭文件
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
         **/
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        Template = new JPanel();
        TitleLBWebDirectoryScan = new JLabel();
        WebDirectoryScanSCLP1 = new JScrollPane();
        WebDirectoryScanOutArea = new JTextArea();
        WebDirectoryScanSCLP2 = new JScrollPane();
        WebDirectoryScanStrusCodeArea = new JTextArea();
        WebDirectoryScanSCLP3 = new JScrollPane();
        WebDirectoryScanURLArea2 = new JTextArea();
        WebDirectoryScanURLTF = new JTextField();
        DictionarySelectBT = new JButton();
        WebDirectoryScanDictionaryPathTF = new JTextField();
        WebDirectoryScanLB1 = new JLabel();
        TCSP = new JSpinner();
        LBTC = new JLabel();
        LBDealy = new JLabel();
        DelaySP = new JSpinner();
        StartBT = new JButton();
        CanelBT = new JButton();
        LBRunTime = new JLabel();
        LBDirectiryNumber = new JLabel();
        DirectiryNumberTF = new JTextField();
        RunTimeTF = new JTextField();
        PB = new JProgressBar();
        LBURL = new JLabel();
        LBDown = new JLabel();
        WDSMatchNumCB = new JComboBox<>();
        LB1 = new JLabel();
        TextOutBT = new JButton();

        //======== this ========
        setToolTipText("\u5bfc\n\u51fa");
        setLayout(null);

        //======== Template ========
        {
            Template.setBackground(null);
            Template.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
            Template.setLayout(null);

            //---- TitleLBWebDirectoryScan ----
            TitleLBWebDirectoryScan.setText("WebDirectoryScan");
            TitleLBWebDirectoryScan.setFont(new Font("Jokerman", Font.PLAIN, 35));
            TitleLBWebDirectoryScan.setForeground(null);
            Template.add(TitleLBWebDirectoryScan);
            TitleLBWebDirectoryScan.setBounds(142, 15, 335, 54);

            //======== WebDirectoryScanSCLP1 ========
            {

                //---- WebDirectoryScanOutArea ----
                WebDirectoryScanOutArea.setEditable(false);
                WebDirectoryScanOutArea.setFont(WebDirectoryScanOutArea.getFont().deriveFont(WebDirectoryScanOutArea.getFont().getSize() + 3f));
                WebDirectoryScanSCLP1.setViewportView(WebDirectoryScanOutArea);
            }
            Template.add(WebDirectoryScanSCLP1);
            WebDirectoryScanSCLP1.setBounds(401, 95, 185, 470);

            //======== WebDirectoryScanSCLP2 ========
            {

                //---- WebDirectoryScanStrusCodeArea ----
                WebDirectoryScanStrusCodeArea.setText("200\n301\n302\n303\n401\n403\n");
                WebDirectoryScanStrusCodeArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
                WebDirectoryScanStrusCodeArea.setLineWrap(true);
                WebDirectoryScanSCLP2.setViewportView(WebDirectoryScanStrusCodeArea);
            }
            Template.add(WebDirectoryScanSCLP2);
            WebDirectoryScanSCLP2.setBounds(52, 263, 52, 250);

            //======== WebDirectoryScanSCLP3 ========
            {

                //---- WebDirectoryScanURLArea2 ----
                WebDirectoryScanURLArea2.setFont(WebDirectoryScanURLArea2.getFont().deriveFont(WebDirectoryScanURLArea2.getFont().getSize() + 3f));
                WebDirectoryScanURLArea2.setEditable(false);
                WebDirectoryScanURLArea2.addCaretListener(e -> WebDirectoryScanURLArea2CaretUpdate(e));
                WebDirectoryScanSCLP3.setViewportView(WebDirectoryScanURLArea2);
            }
            Template.add(WebDirectoryScanSCLP3);
            WebDirectoryScanSCLP3.setBounds(70, 184, 300, 36);

            //---- WebDirectoryScanURLTF ----
            WebDirectoryScanURLTF.setText("http://{dir}");
            WebDirectoryScanURLTF.addCaretListener(e -> WebDirectoryScanURLTFCaretUpdate(e));
            Template.add(WebDirectoryScanURLTF);
            WebDirectoryScanURLTF.setBounds(34, 100, 335, 30);

            //---- DictionarySelectBT ----
            DictionarySelectBT.setText("\u9009\u62e9\u5b57\u5178");
            DictionarySelectBT.addActionListener(e -> DictionarySelectBTActionPerformed(e));
            Template.add(DictionarySelectBT);
            DictionarySelectBT.setBounds(34, 142, 78, 30);

            //---- WebDirectoryScanDictionaryPathTF ----
            WebDirectoryScanDictionaryPathTF.setEditable(false);
            Template.add(WebDirectoryScanDictionaryPathTF);
            WebDirectoryScanDictionaryPathTF.setBounds(114, 142, 255, 30);

            //---- WebDirectoryScanLB1 ----
            WebDirectoryScanLB1.setText("\u54cd\u5e94\u7801:");
            Template.add(WebDirectoryScanLB1);
            WebDirectoryScanLB1.setBounds(52, 235, 51, 22);

            //---- TCSP ----
            TCSP.setModel(new SpinnerNumberModel(50, null, null, 1));
            Template.add(TCSP);
            TCSP.setBounds(219, 238, 100, 30);

            //---- LBTC ----
            LBTC.setText("\u7ebf\u7a0b\u6570:");
            Template.add(LBTC);
            LBTC.setBounds(147, 245, 47, LBTC.getPreferredSize().height);

            //---- LBDealy ----
            LBDealy.setText("\u8d85\u65f6\u65f6\u95f4:");
            Template.add(LBDealy);
            LBDealy.setBounds(147, 300, 59, 17);

            //---- DelaySP ----
            DelaySP.setModel(new SpinnerNumberModel(10000, null, null, 1));
            Template.add(DelaySP);
            DelaySP.setBounds(219, 293, 100, 30);

            //---- StartBT ----
            StartBT.setText("\u5f00\u59cb");
            StartBT.addActionListener(e -> StartBTActionPerformed(e));
            Template.add(StartBT);
            StartBT.setBounds(180, 500, 115, 45);

            //---- CanelBT ----
            CanelBT.setText("\u505c\u6b62");
            CanelBT.setVisible(false);
            CanelBT.addActionListener(e -> CanelBTActionPerformed(e));
            Template.add(CanelBT);
            CanelBT.setBounds(180, 500, 115, 45);

            //---- LBRunTime ----
            LBRunTime.setText("\u8fd0\u884c\u72b6\u6001:");
            Template.add(LBRunTime);
            LBRunTime.setBounds(146, 410, 59, 17);

            //---- LBDirectiryNumber ----
            LBDirectiryNumber.setText("\u5b57\u5178\u6761\u76ee\u6570:");
            Template.add(LBDirectiryNumber);
            LBDirectiryNumber.setBounds(146, 355, 70, 17);

            //---- DirectiryNumberTF ----
            DirectiryNumberTF.setEditable(false);
            DirectiryNumberTF.setHorizontalAlignment(SwingConstants.CENTER);
            Template.add(DirectiryNumberTF);
            DirectiryNumberTF.setBounds(219, 348, 100, 30);

            //---- RunTimeTF ----
            RunTimeTF.setEditable(false);
            RunTimeTF.setHorizontalAlignment(SwingConstants.CENTER);
            RunTimeTF.setText("Waiting");
            Template.add(RunTimeTF);
            RunTimeTF.setBounds(219, 403, 100, 30);
            Template.add(PB);
            PB.setBounds(158, 457, PB.getPreferredSize().width, 18);

            //---- LBURL ----
            LBURL.setText("\u9884\n\u89c8");
            Template.add(LBURL);
            LBURL.setBounds(40, 184, 25, 36);

            //---- LBDown ----
            LBDown.setText("\u5b57\u5178\u5185\u5bb9\u4f1a\u88ab\"{dir}\"\u66ff\u6362::\u7ebf\u7a0b\u6570\u5efa\u8bae\u6700\u592750::\u54cd\u5e94\u7801\u53ef\u81ea\u884c\u6dfb\u52a0\u6216\u5220\u9664.\u522b\u5fd8\u4e86\u6362\u884c");
            Template.add(LBDown);
            LBDown.setBounds(4, 570, 616, LBDown.getPreferredSize().height);

            //---- WDSMatchNumCB ----
            WDSMatchNumCB.setModel(new DefaultComboBoxModel<>(new String[] {
                "ALL"
            }));
            WDSMatchNumCB.addItemListener(e -> WDSMatchNumCBItemStateChanged(e));
            Template.add(WDSMatchNumCB);
            WDSMatchNumCB.setBounds(500, 75, 75, 21);

            //---- LB1 ----
            LB1.setText("\u663e\u793a\u7684\u54cd\u5e94\u7801:");
            Template.add(LB1);
            LB1.setBounds(413, 77, 85, LB1.getPreferredSize().height);

            //---- TextOutBT ----
            TextOutBT.setIcon(new ImageIcon(getClass().getResource("/img/icon/save-fill.png")));
            TextOutBT.addActionListener(e -> TextOutBTActionPerformed(e));
            Template.add(TextOutBT);
            TextOutBT.setBounds(378, 100, 25, 45);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < Template.getComponentCount(); i++) {
                    Rectangle bounds = Template.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = Template.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                Template.setMinimumSize(preferredSize);
                Template.setPreferredSize(preferredSize);
            }
        }
        add(Template);
        Template.setBounds(0, 0, 620, 590);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel Template;
    private JLabel TitleLBWebDirectoryScan;
    private JScrollPane WebDirectoryScanSCLP1;
    private JTextArea WebDirectoryScanOutArea;
    private JScrollPane WebDirectoryScanSCLP2;
    private JTextArea WebDirectoryScanStrusCodeArea;
    private JScrollPane WebDirectoryScanSCLP3;
    private JTextArea WebDirectoryScanURLArea2;
    private JTextField WebDirectoryScanURLTF;
    private JButton DictionarySelectBT;
    private JTextField WebDirectoryScanDictionaryPathTF;
    private JLabel WebDirectoryScanLB1;
    private JSpinner TCSP;
    private JLabel LBTC;
    private JLabel LBDealy;
    private JSpinner DelaySP;
    private JButton StartBT;
    private JButton CanelBT;
    private JLabel LBRunTime;
    private JLabel LBDirectiryNumber;
    private JTextField DirectiryNumberTF;
    private JTextField RunTimeTF;
    private JProgressBar PB;
    private JLabel LBURL;
    private JLabel LBDown;
    private JComboBox<String> WDSMatchNumCB;
    private JLabel LB1;
    private JButton TextOutBT;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    protected void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        fileChooser.setSelectedFile(new File("ALL.txt"));
        int retval = fileChooser.showSaveDialog(TextOutBT);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getParentFile(), file.getName() + ".txt");
            }
            try {
                OutputStreamWriter OSW = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
                for (String a:bean.getResultOut().split("\n")) {
                    if(a.substring(0,3).equals(WDSMatchNumCB.getSelectedItem().toString()))
                    {
                        OSW.write(a.replace("::","::"+bean.getURL().replace("{dir}",""))+"\n");
                    }else if(WDSMatchNumCB.getSelectedItem().toString().equals("ALL")&&WDSMatchNumCB.getSelectedItem().toString()!="null")
                    {
                        OSW.write(a.replace("::","::"+bean.getURL().replace("{dir}",""))+"\n");
                    }
                    //System.out.println(a.substring(0,3));
                }
                OSW.flush();
                OSW.close();
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}