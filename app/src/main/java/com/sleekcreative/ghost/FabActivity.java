package com.sleekcreative.ghost;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class FabActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        findViewById(R.id.pink_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FabActivity.this, "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.setter);
        button.setSize(FloatingActionButton.SIZE_MINI);
        button.setColorNormalResId(R.color.pink);
        button.setColorPressedResId(R.color.pink_pressed);
        button.setIcon(R.drawable.ic_fab_star);

        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        ((FloatingActionsMenu) findViewById(R.id.multiple_actions)).addButton(actionC);

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });
    }
}
