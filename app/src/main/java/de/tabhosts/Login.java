package de.tabhosts;

import java.util.logging.Logger;

import de.db.LoginOrRegisterUser;
import de.mybeschwerde.EmailValidator;
import de.mybeschwerde.R;
import de.user.shared.SessionManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	private static final Logger logger = Logger.getLogger(Login.class.getName());
	
	// for login
	private EditText input_email = null;
	private EditText input_Password = null;
	private TextView attempts;
	private ImageButton login;
	int counter = 3;
	// for registration
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFirstName;
	EditText inputLastName;
	EditText inputEmail;
	EditText inputRegPassword;
	EditText inputRegPassword2;
	TextView registerErrorMsg;

	private int currentViewId = -1;
//	JSONObject json_user;
	SessionManager session;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		session = (SessionManager) getApplicationContext();
		// check therefore user is registered
		if (session.isLogged()) {
			goToDashboard();
		} else {

            initStepForLogin();

			ImageButton registerScreen = (ImageButton) findViewById(R.id.link_to_register);
			registerScreen.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// setCurrentViewById(R.layout.register);
					goToRegister();
					logger.info("maked to Register site");
				}
			});
		}
	}

    private void initStepForLogin() {
        setCurrentViewById(R.layout.login);
        input_email = (EditText) findViewById(R.id.user_email);
        input_Password = (EditText) findViewById(R.id.user_passwd);
        logger.info("INFO : username and password has been readed");
       // attempts = (TextView) findViewById(R.id.error_text);
        //attempts.setText(Integer.toString(counter));
        login = (ImageButton) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EmailValidator ev = new EmailValidator();
                if(input_email.getText().length()==0 || !ev.validate(input_email.getText().toString())){
                    initNewDialog("Bitte geben Sie ein gültige Email-Adresse ein!");
                }else {
                    login();
                }
            }
        });
    }

    private void initNewDialog(String meldung) {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom);
        SpannableString title=  new SpannableString(meldung);
        title.setSpan(new RelativeSizeSpan(0.9f), 0, title.length(), 0);
        title.setSpan(new ForegroundColorSpan(Color.RED), 0, title.length(), 0);
        dialog.setTitle(title);


        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void login() {
		
		if (session.isLogged()) {
			// TODO : here must load new layout for user interface
			goToDashboard();
		} else {
			// TODO: here maybe email instead username!!!
			String username = input_email.getText().toString();
			String password = input_Password.getText().toString();
			
			LoginOrRegisterUser logUser = new LoginOrRegisterUser();
			logUser.loginUser(username, password);
			
			String uName = logUser.getUser().getFirstName()+" "+logUser.getUser().getLastName();
			String uEmail = logUser.getUser().getEmail();
			Long uId = logUser.getUser().getId();
			
			session.createGlobalUser(uName, uEmail, uId);
			session.LOGGED = true;
			
			goToDashboard();
		}
//		else {
//			// show the failure message in display
//			Toast.makeText(getApplicationContext(), logUser.getMessage(), Toast.LENGTH_LONG).show();//
//		}
		
	}


	public void setCurrentViewById(int id) {
		setContentView(id);
		currentViewId = id;
		overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	public int getCurrentViewById() {
		return currentViewId;
	}

    public void goToLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                initStepForLogin();
               // login();
            }
        });

        try {
            setCurrentViewById(R.layout.login);
            ImageButton registerScreen = (ImageButton) findViewById(R.id.link_to_register);

            // Listening to register new account link
            registerScreen.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    goToRegister();
                    logger.info("Go to Register site");
                }
            });
        } catch (Exception e) {
            logger.warning("Fehler : " + e.getMessage());
        }
    }

	public void goToRegister() {
		setCurrentViewById(R.layout.register);
		// Importing all assets like buttons, text fields
		inputFirstName = (EditText) findViewById(R.id.reg_firstname);
		inputLastName = (EditText) findViewById(R.id.reg_lastname);
		inputEmail = (EditText) findViewById(R.id.reg_email);
		inputRegPassword = (EditText) findViewById(R.id.reg_password);
		inputRegPassword2 = (EditText) findViewById(R.id.reg_password2);
		btnRegister = (Button) findViewById(R.id.btnRegister);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
                EmailValidator ev = new EmailValidator();
				String firstname = inputFirstName.getText().toString();
				String lastname = inputLastName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputRegPassword.getText().toString();
				String password2 = inputRegPassword2.getText().toString();
				logger.info("INFO : Password are compared...");
				if (firstname.length() == 0) {
					//inputFirstName.setBackgroundColor(Color.RED);
                    initNewDialog("Bitte geben Sie Ihre Vorname ein!");
				}else if(lastname.length() == 0){
                    initNewDialog("Bitte geben Sie Ihre Nachname ein!");
					//inputLastName.setBackgroundColor(Color.RED);
				} else if (ev.validate(email.toString())) {
					//inputEmail.setBackgroundColor(Color.RED);
                    initNewDialog("Bitte geben Sie ein gültige Email-Adresse ein!");
				}
				// check if both password matches
				if (!password.equals(password2)) {
					Toast.makeText(getApplicationContext(),"Passwoerter sind nicht eindeutig!", Toast.LENGTH_LONG).show();//
					inputRegPassword.setText("");
					inputRegPassword2.setText("");
					goToRegister();
				} else {
					LoginOrRegisterUser regUser = new LoginOrRegisterUser();
					regUser.registerUser(firstname, lastname, email, password);
					if(regUser.isInserted()) {
						String uName = regUser.getUser().getFirstName()+" "+regUser.getUser().getLastName();
						String uMail = regUser.getUser().getEmail();
						Long uId = regUser.getUser().getId();
						
						session.createGlobalUser(uName, uMail, uId);
						
						goToDashboard();
					}else {
						// show the failure message in display
						Toast.makeText(getApplicationContext(), regUser.getMessage(), Toast.LENGTH_LONG).show();//
					}

				}

			}
		});


		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

		// Listening to register new account link
		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// setCurrentViewById(R.layout.register);
				goToLogin();
				logger.info("Go to login site");
			}
		});

	}

	private Boolean comparePasswords(String password, String password2) {
		if (password.equals(password2)) {
			return true;
		} else {
			System.out.println("Yout password are not equals");
			return false;
		}
	}
	
	private void goToDashboard() {
		setCurrentViewById(R.layout.dashboard);
		TextView u_name = (TextView) findViewById(R.id.user_name);
		u_name.setText(session.getName());
	}
	
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
    	// TODO not implemented
        return true;
    }
}
