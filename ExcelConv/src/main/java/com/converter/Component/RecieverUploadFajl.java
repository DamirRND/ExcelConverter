package com.converter.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;

@SuppressWarnings("serial")
public class RecieverUploadFajl implements Receiver {
    public ByteArrayOutputStream baos = new ByteArrayOutputStream();
    @Override
    public OutputStream receiveUpload(final String filename, final String MIMEType) {
        return new OutputStream() {
            
            @Override
            public void write(final int b) {
            	baos.write(b);
            }
        };
    }

    public ByteArrayOutputStream getBaos() {
		return baos;
	}
}