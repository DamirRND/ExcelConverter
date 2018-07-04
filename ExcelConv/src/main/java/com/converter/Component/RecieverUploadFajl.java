package com.converter.Component;

import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;

@SuppressWarnings("serial")
public class RecieverUploadFajl implements Receiver {
    private int counter;
    private int total;
    private boolean sleep;

    @Override
    public OutputStream receiveUpload(final String filename, final String MIMEType) {
        counter = 0;
        total = 0;
        return new OutputStream() {
            private static final int searchedByte = '\n';

            @Override
            public void write(final int b) {
                total++;
                if (b == searchedByte) {
                    counter++;
                }
                if (sleep && total % 1000 == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public int getLineBreakCount() {
        return counter;
    }

    public void setSlow(boolean value) {
        sleep = value;
    }
}