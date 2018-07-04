package com.converter.Views;

import com.converter.Component.RecieverUploadFajl;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial") 
public class ImportFajl extends Window implements Upload.StartedListener, Upload.ProgressListener,
		Upload.FailedListener, Upload.SucceededListener, Upload.FinishedListener {
	private final Label state = new Label();
	private final Label result = new Label();
	public Label fileName = new Label();
	private final Label textualProgress = new Label();

	private final ProgressBar progressBar = new ProgressBar();
	private final Button cancelButton;
	private final RecieverUploadFajl counter;

	public ImportFajl(final Upload upload, final RecieverUploadFajl lineBreakCounter) {
		super("Status");
		this.counter = lineBreakCounter;

		addStyleName("upload-info");

		setResizable(false);
		setDraggable(false);

		final FormLayout uploadInfoLayout = new FormLayout();
		setContent(uploadInfoLayout);
		uploadInfoLayout.setMargin(true);

		final HorizontalLayout stateLayout = new HorizontalLayout();
		stateLayout.setSpacing(true);
		stateLayout.addComponent(state);

		cancelButton = new Button("Cancel");
		cancelButton.addClickListener(event -> upload.interruptUpload());
		cancelButton.setVisible(false);
		cancelButton.setStyleName("small");
		stateLayout.addComponent(cancelButton);

		stateLayout.setCaption("Trenutno stanje");
		state.setValue("Idle");
		uploadInfoLayout.addComponent(stateLayout);

		fileName.setCaption("Ime fajla");
		uploadInfoLayout.addComponent(fileName);

		result.setCaption("Reciever upload error");
		uploadInfoLayout.addComponent(result);

		progressBar.setCaption("Progress");
		progressBar.setVisible(false);
		uploadInfoLayout.addComponent(progressBar);

		textualProgress.setVisible(false);
		uploadInfoLayout.addComponent(textualProgress);

		upload.addStartedListener(this);
		upload.addProgressListener(this);
		upload.addFailedListener(this);
		upload.addSucceededListener(this);
		upload.addFinishedListener(this);

	}

	@Override
	public void uploadFinished(final FinishedEvent event) {
		state.setValue("Idle");
		progressBar.setVisible(false);
		textualProgress.setVisible(false);
		cancelButton.setVisible(false);
	}

	@Override
	public void uploadStarted(final StartedEvent event) {
		progressBar.setValue(0f);
		progressBar.setVisible(true);
		UI.getCurrent().setPollInterval(500);
		textualProgress.setVisible(true);
		state.setValue("Uploading");
		fileName.setValue(event.getFilename());

		cancelButton.setVisible(true);
	}

	@Override
	public void updateProgress(final long readBytes, final long contentLength) {
		progressBar.setValue(readBytes / (float) contentLength);
		textualProgress.setValue("Procesiurano " + readBytes + " bytes of " + contentLength);
		result.setValue(counter.getLineBreakCount() + " (counting...)");
	}

	@Override
	public void uploadSucceeded(final SucceededEvent event) {
		result.setValue(counter.getLineBreakCount() + " (total)");
	}

	@Override
	public void uploadFailed(final FailedEvent event) {
		result.setValue(counter.getLineBreakCount() + " (upload prekinut na "
				+ Math.round(100 * progressBar.getValue()) + "%)");
	}
}

