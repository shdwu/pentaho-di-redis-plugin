package cn.danielw.pentaho.di.plugin.step.redis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class RedisOutputStepDialog extends BaseStepDialog implements StepDialogInterface {

    /**
     * The PKG member is used when looking up internationalized strings.
     * The properties file with localized keys is expected to reside in
     * {the package of the class specified}/messages/messages_{locale}.properties
     */
    private static Class<?> PKG = RedisOutputStepMeta.class;

    // this is the object the stores the step's settings
    // the dialog reads the settings from it when opening
    // the dialog writes the settings to it when confirmed
    private RedisOutputStepMeta meta;

    private Text textRedisCommand;
    private Text textRedisUrl;
    private Text textRedisDb;
    private Text textRedisAuth;

    /**
     * The constructor should simply invoke super() and save the incoming meta
     * object to a local variable, so it can conveniently read and write settings
     * from/to it.
     *
     * @param parent    the SWT shell to open the dialog in
     * @param in        the meta object holding the step's settings
     * @param transMeta transformation description
     * @param sname     the step name
     */
    public RedisOutputStepDialog(Shell parent, Object in, TransMeta transMeta, String sname) {
        super(parent, (BaseStepMeta) in, transMeta, sname);
        meta = (RedisOutputStepMeta) in;
    }

    /**
     * This method is called by Spoon when the user opens the settings dialog of the step.
     * It should open the dialog and return only once the dialog has been closed by the user.
     * <p>
     * If the user confirms the dialog, the meta object (passed in the constructor) must
     * be updated to reflect the new step settings. The changed flag of the meta object must
     * reflect whether the step configuration was changed by the dialog.
     * <p>
     * If the user cancels the dialog, the meta object must not be updated, and its changed flag
     * must remain unaltered.
     * <p>
     * The open() method must return the name of the step after the user has confirmed the dialog,
     * or null if the user cancelled the dialog.
     */
    @Override
    public String open() {

        // store some convenient SWT variables
        Shell parent = getParent();
        Display display = parent.getDisplay();

        // SWT code for preparing the dialog
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX);
        props.setLook(shell);
        setShellImage(shell, meta);

        // Save the value of the changed flag on the meta object. If the user cancels
        // the dialog, it will be restored to this saved value.
        // The "changed" variable is inherited from BaseStepDialog
        changed = meta.hasChanged();

        // The ModifyListener used on all controls. It will update the meta object to
        // indicate that changes are being made.
        ModifyListener lsMod = new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent modifyEvent) {
                meta.setChanged();
            }
        };

        // ------------------------------------------------------- //
        // SWT code for building the actual settings dialog        //
        // ------------------------------------------------------- //
        FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = Const.FORM_MARGIN;
        formLayout.marginHeight = Const.FORM_MARGIN;

        shell.setLayout(formLayout);
        shell.setText(BaseMessages.getString(PKG, "RedisOutputStep.Name"));

        int middle = props.getMiddlePct();
        int margin = Const.MARGIN;

        // Step name label
        {
            wlStepname = new Label(shell, SWT.RIGHT);
            wlStepname.setText(BaseMessages.getString(PKG, "System.Label.StepName"));
            props.setLook(wlStepname);
            fdlStepname = new FormData();
            fdlStepname.left = new FormAttachment(0, 0);
            fdlStepname.right = new FormAttachment(middle, -margin);
            fdlStepname.top = new FormAttachment(0, margin);
            wlStepname.setLayoutData(fdlStepname);
        }
        // Step name text
        {
            wStepname = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
            wStepname.setText(stepname);
            props.setLook(wStepname);
            wStepname.addModifyListener(lsMod);
            fdStepname = new FormData();
            fdStepname.left = new FormAttachment(middle, 0);
            fdStepname.top = new FormAttachment(0, margin);
            fdStepname.right = new FormAttachment(100, 0);
            wStepname.setLayoutData(fdStepname);
        }
        // command label
        {
            Label label = new Label(shell, SWT.RIGHT);
            label.setText(BaseMessages.getString(PKG, "RedisOutputStep.Dialog.Command"));
            props.setLook(label);
            FormData formData = new FormData();
            formData.left = new FormAttachment(0, 0);
            formData.right = new FormAttachment(middle, -margin);
            formData.top = new FormAttachment(wStepname, margin);
            label.setLayoutData(formData);
        }
        // command text
        {
            textRedisCommand = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
            props.setLook(textRedisCommand);
            textRedisCommand.addModifyListener(lsMod);
            FormData formData = new FormData();
            formData.left = new FormAttachment(middle, 0);
            formData.right = new FormAttachment(100, 0);
            formData.top = new FormAttachment(wStepname, margin);
            textRedisCommand.setLayoutData(formData);
        }
        // url label
        {
            Label label = new Label(shell, SWT.RIGHT);
            label.setText(BaseMessages.getString(PKG, "RedisOutputStep.Dialog.Url"));
            props.setLook(label);
            FormData formData = new FormData();
            formData.left = new FormAttachment(0, 0);
            formData.right = new FormAttachment(middle, -margin);
            formData.top = new FormAttachment(textRedisCommand, margin);
            label.setLayoutData(formData);
        }
        // url text
        {
            textRedisUrl = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
            props.setLook(textRedisUrl);
            textRedisUrl.addModifyListener(lsMod);
            FormData formData = new FormData();
            formData.left = new FormAttachment(middle, 0);
            formData.right = new FormAttachment(100, 0);
            formData.top = new FormAttachment(textRedisCommand, margin);
            textRedisUrl.setLayoutData(formData);
        }
        // db label
        {
            Label label = new Label(shell, SWT.RIGHT);
            label.setText(BaseMessages.getString(PKG, "RedisOutputStep.Dialog.Db"));
            props.setLook(label);
            FormData formData = new FormData();
            formData.left = new FormAttachment(0, 0);
            formData.right = new FormAttachment(middle, -margin);
            formData.top = new FormAttachment(textRedisUrl, margin);
            label.setLayoutData(formData);
        }
        // db text
        {
            textRedisDb = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
            props.setLook(textRedisDb);
            textRedisDb.addModifyListener(lsMod);
            FormData formData = new FormData();
            formData.left = new FormAttachment(middle, 0);
            formData.right = new FormAttachment(100, 0);
            formData.top = new FormAttachment(textRedisUrl, margin);
            textRedisDb.setLayoutData(formData);
        }
        // auth label
        {
            Label label = new Label(shell, SWT.RIGHT);
            label.setText(BaseMessages.getString(PKG, "RedisOutputStep.Dialog.Auth"));
            props.setLook(label);
            FormData formData = new FormData();
            formData.left = new FormAttachment(0, 0);
            formData.right = new FormAttachment(middle, -margin);
            formData.top = new FormAttachment(textRedisDb, margin);
            label.setLayoutData(formData);
        }
        // db text
        {
            textRedisAuth = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
            props.setLook(textRedisAuth);
            textRedisAuth.addModifyListener(lsMod);
            FormData formData = new FormData();
            formData.left = new FormAttachment(middle, 0);
            formData.right = new FormAttachment(100, 0);
            formData.top = new FormAttachment(textRedisDb, margin);
            textRedisAuth.setLayoutData(formData);
        }


        // OK and cancel buttons
        wOK = new Button(shell, SWT.PUSH);
        wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));
        wCancel = new Button(shell, SWT.PUSH);
        wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));

        setButtonPositions(new Button[]{wOK, wCancel}, margin, null);
        // BaseStepDialog.positionBottomButtons(shell, new Button[] { wOK, wCancel }, margin, textRedisUrl);

        // Add listeners for cancel and OK
        lsCancel = new Listener() {
            @Override
            public void handleEvent(Event event) {
                cancel();
            }
        };
        lsOK = new org.eclipse.swt.widgets.Listener() {

            @Override
            public void handleEvent(Event event) {
                ok();
            }
        };

        wCancel.addListener(SWT.Selection, lsCancel);
        wOK.addListener(SWT.Selection, lsOK);

        // default listener (for hitting "enter")
        lsDef = new SelectionAdapter() {
            public void widgetDefaultSelected(SelectionEvent e) {
                ok();
            }
        };
        wStepname.addSelectionListener(lsDef);
        textRedisCommand.addSelectionListener(lsDef);
        textRedisUrl.addSelectionListener(lsDef);

        // Detect X or ALT-F4 or something that kills this window and cancel the dialog properly
        shell.addShellListener(new ShellAdapter() {
            public void shellClosed(ShellEvent e) {
                cancel();
            }
        });

        // Set/Restore the dialog size based on last position on screen
        // The setSize() method is inherited from BaseStepDialog

        setSize();

        // populate the dialog with the values from the meta object
        populateDialog();

        // restore the changed flag to original value, as the modify listeners fire during dialog population
        meta.setChanged(changed);

        // open dialog and enter event loop
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
				display.sleep();
			}
        }

        // at this point the dialog has closed, so either ok() or cancel() have been executed
        // The "stepname" variable is inherited from BaseStepDialog
        return stepname;
    }

    /**
     * This helper method puts the step configuration stored in the meta object
     * and puts it into the dialog controls.
     */
    private void populateDialog() {
        wStepname.selectAll();
        textRedisCommand.setText(meta.getCommand());
        textRedisUrl.setText(meta.getUrl());
        textRedisDb.setText(meta.getDb());
        textRedisAuth.setText(meta.getAuth());
    }

    /**
     * Called when the user cancels the dialog.
     */
    private void cancel() {
        // The "stepname" variable will be the return value for the open() method.
        // Setting to null to indicate that dialog was cancelled.
        stepname = null;
        // Restoring original "changed" flag on the met aobject
        meta.setChanged(changed);
        // close the SWT dialog window
        dispose();
    }

    /**
     * Called when the user confirms the dialog
     */
    private void ok() {
        // The "stepname" variable will be the return value for the open() method.
        // Setting to step name from the dialog control
        stepname = wStepname.getText();
        // Setting the  settings to the meta object
        meta.setCommand(textRedisCommand.getText());
        meta.setUrl(textRedisUrl.getText());
        meta.setDb(textRedisDb.getText());
        meta.setAuth(textRedisAuth.getText());
        // close the SWT dialog window
        dispose();
    }
}
