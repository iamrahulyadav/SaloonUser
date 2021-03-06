package app.saloonuser.craftystudio.saloonuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Visibility;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.w3c.dom.Text;

import utils.FireBaseHandler;
import utils.User;

public class UserProfileActivity extends AppCompatActivity {

    TextView mUserName, mUserAge, mUserGender, mUserMobileNumber;
    ImageView mUserProfileImage;
    Button mAddServiceButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            //getting window component
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // Do something for lollipop and above versions
            //animation
            final Explode explode = new Explode();
            explode.setDuration(500);
            explode.setMode(Visibility.MODE_IN);
            getWindow().setEnterTransition(explode);
            getWindow().setAllowEnterTransitionOverlap(true);

            //animate shake in ToolBar
            YoYo.with(Techniques.DropOut)
                    .duration(1000)
                    .repeat(1)
                    .playOn(toolbar);

        } else {
            // do something for phones running an SDK before lollipop
        }


        //views
        mUserName = (TextView) findViewById(R.id.userProfile_name_textview);
        mUserAge = (TextView) findViewById(R.id.userProfile_age_textview);
        mUserGender = (TextView) findViewById(R.id.userProfile_gender_textview);
        mUserMobileNumber = (TextView) findViewById(R.id.userProfile_number_textview);

        mUserProfileImage = (ImageView) findViewById(R.id.userProfile_image_imageview);

        mAddServiceButton = (Button) findViewById(R.id.userProfile_addDetail_button);

        mAddServiceButton.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new FireBaseHandler().downloadUser(LoginActivity.USER.getUserUID(), new FireBaseHandler.OnUserlistener() {
            @Override
            public void onUserDownLoad(User user, boolean isSuccessful) {
                if (isSuccessful) {
                    if (user != null) {
                        updateUI(user);
                        if (user.getUserName() != null) {
                            if (user.getUserName().isEmpty()) {
                                mAddServiceButton.setVisibility(View.VISIBLE);
                                //animate Button
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                    // Do something for lollipop and above versions
                                    YoYo.with(Techniques.Shake)
                                            .duration(1000)
                                            .repeat(1)
                                            .playOn(mAddServiceButton);

                                } else {
                                    // do something for phones running an SDK before lollipop
                                }


                            }
                        } else {
                            mAddServiceButton.setVisibility(View.VISIBLE);
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                // Do something for lollipop and above versions
                                YoYo.with(Techniques.Shake)
                                        .duration(1000)
                                        .repeat(1)
                                        .playOn(mAddServiceButton);

                            } else {
                                // do something for phones running an SDK before lollipop
                            }


                        }
                    } else {
                        user = LoginActivity.USER;
                        user.setUserGender("Male");
                        user.setUserName("Hey Handsome");
                        user.setUserAge(20);
                        updateUI(user);
                        mAddServiceButton.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onUserUpload(boolean isSuccessful) {

            }
        });


        mAddServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, UserDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }

    public void updateUI(User user) {
        mUserName.setText(user.getUserName());
        mUserAge.setText(user.getUserAge() + "");
        mUserGender.setText(user.getUserGender());

        if (user.getUserGender() != null) {
            if (user.getUserGender().equalsIgnoreCase("Male")) {
                mUserProfileImage.setImageResource(R.drawable.malefinal);

            } else {
                mUserProfileImage.setImageResource(R.drawable.female_copy);
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                // Do something for lollipop and above versions
                //animate shake in Saloon Name
                YoYo.with(Techniques.DropOut)
                        .duration(1000)
                        .repeat(1)
                        .playOn(mUserProfileImage);
            } else {
                // do something for phones running an SDK before lollipop
            }


        }

        mUserMobileNumber.setText(user.getUserPhoneNumber());

    }

    @Override
    public void onBackPressed() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
        super.onBackPressed();

    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

}
