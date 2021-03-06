import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ��������� on 02.10.2015.
 */
@SuppressWarnings("deprecated")
public class PreServerThread {
    public PreServerThread() {
        JFrame checkFrame = new JFrame("Key");
        checkFrame.setLayout(new FlowLayout());
        checkFrame.setSize(400, 100);
        checkFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkFrame.setLocationRelativeTo(null);

        JTextField field = new JTextField(20);
        JLabel label = new JLabel("Please, enter your key");
        JButton submit = new JButton("Submit");
        JButton trial = new JButton("Trial");

        checkFrame.add(field);
        checkFrame.add(label);
        checkFrame.add(submit);
        checkFrame.add(trial);
        checkFrame.setVisible(true);

        Thread checkThread = new Thread(new TimeChecker(System.currentTimeMillis()));

        checkThread.start();
        trial.addActionListener(e -> {
            checkThread.stop();
            JOptionPane.showMessageDialog(checkFrame, "Now you have 10 seconds");
            checkFrame.setVisible(false);
            new Thread(new ServerThread()).start();
            Thread thread = new Thread(() -> {
                long allowedTime = 10000;
                long startTime = System.currentTimeMillis();
                while (true) {
                    if ((System.currentTimeMillis() - startTime) > allowedTime) {
                        JOptionPane.showMessageDialog(checkFrame, "See you!");
                        Runtime.getRuntime().exit(0);
                    }
                }
            });
            thread.start();
        });
        submit.addActionListener(e ->{
            checkThread.stop();
            String submitted = DigestUtils.md5Hex(field.getText());
            String s = (MyRequest.sendPOST(submitted, GetDriveInfo.getSerialNumber()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(s.equals("OK")){
                if(new XMLCreator().makeFile(submitted)) {
                    new Thread(new ServerThread()).start();
                    checkFrame.setVisible(false);
                }
            }
        });
    }
}
