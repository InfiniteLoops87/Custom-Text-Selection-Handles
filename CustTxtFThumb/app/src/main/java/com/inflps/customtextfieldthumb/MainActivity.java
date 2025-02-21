package com.inflps.customtextfieldthumb;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import java.lang.reflect.Field;

public class MainActivity extends Activity {
	
	private EditText edittexttittle;
	private EditText edittext1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		edittexttittle = findViewById(R.id.edittexttittle);
		edittext1 = findViewById(R.id.edittext1);
	}
	
	private void initializeLogic() {
		edittexttittle.setText("My note");
		edittext1.setText(Html.fromHtml("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Custom Text Selection Handles - Documentation</title>\n</head>\n<body>\n\n    <h1><strong>Custom Text Selection Handles - Documentation</strong></h1>\n    <br>\n\n    <h2>1. Purpose</h2>\n    <p>This snippet allows you to customize the default text selection handles in an <code>TextView</code> or <code>EditText</code> field in Android. By modifying internal fields using reflection, it replaces the default left, right, and middle selection handles with custom drawable resources.</p>\n    <br>\n\n    <h2>2. Required Imports</h2>\n    <p>Ensure the following import is included for reflection:</p>\n    <pre><code>import java.lang.reflect.Field;</code></pre>\n    <p><strong>Note:</strong> In Sketchware, importing <code>android.graphics.drawable.Drawable</code> is unnecessary.</p>\n    <br>\n\n    <h2>3. Available Selection Handles</h2>\n    <p>You can modify the following selection handles:</p>\n    <ul>\n        <li><strong>Left Handle:</strong> <code>mTextSelectHandleLeftRes</code></li>\n        <li><strong>Right Handle:</strong> <code>mTextSelectHandleRightRes</code></li>\n        <li><strong>Middle Handle:</strong> <code>mTextSelectHandleRes</code> (used for dragging selection)</li>\n    </ul>\n    <br>\n\n    <h2>4. Compatibility with Other Views</h2>\n    <p><strong>1. WebView Support:</strong></p>\n    <ul>\n        <li>This method does <strong>not</strong> work for <code>WebView</code> since it renders web content using Chromium's engine.</li>\n        <li>To customize selection handles in a WebView, you must use **CSS & JavaScript** instead of Android's native selection system.</li>\n        <li>Example: Override selection styles using <code>::selection</code> in CSS.</li>\n    </ul>\n    \n    <p><strong>2. Other Views:</strong></p>\n    <ul>\n        <li>This method only applies to <code>TextView</code> and its subclasses (e.g., <code>EditText</code>).</li>\n        <li>It does <strong>not</strong> work for buttons, checkboxes, or custom views that do not use Android's text selection system.</li>\n        <li>For custom views, a possible workaround is creating a **custom selection mechanism** using <code>onTouchListener</code>.</li>\n    </ul>\n    <br>\n\n    <h2>5. How It Works</h2>\n    <ol>\n        <li>Uses reflection to access private fields inside the <code>TextView</code> class.</li>\n        <li>Replaces the selection handle resources with custom drawable files from <code>res/drawable</code>.</li>\n        <li>Updates the <code>mEditor</code> object to apply the changes dynamically.</li>\n    </ol>\n    <br>\n\n    <h2>6. Disadvantages & Considerations</h2>\n    <ul>\n        <li><strong>Reflection Risks:</strong> Accessing private fields may break in future Android versions.</li>\n        <li><strong>Device Compatibility:</strong> May not work on all devices due to OEM modifications.</li>\n        <li><strong>Performance Impact:</strong> Reflection is slower compared to direct API usage.</li>\n        <li><strong>Limited Official Support:</strong> Google discourages using reflection for modifying private fields.</li>\n    </ul>\n    <br>\n\n    <h2>7. Best Practices</h2>\n    <ul>\n        <li>Use this method only if absolutely necessary.</li>\n        <li>Test on multiple Android versions to ensure compatibility.</li>\n        <li>Keep backup implementations in case the method stops working in newer updates.</li>\n    </ul>\n    <br>\n\n    <h2>8. Alternative Solutions</h2>\n    <p>If modifying text selection handles is essential, consider:</p>\n    <ul>\n        <li>Using <code>android:theme</code> to customize text selection styles.</li>\n        <li>Exploring third-party libraries that provide selection customization.</li>\n        <li>Implementing a custom text editor with fully controlled UI elements.</li>\n        <li>For <strong>WebView</strong>, use CSS and JavaScript to customize text selection.</li>\n    </ul>\n    <br>\n\n    <h2>9. Conclusion</h2>\n    <p>While this snippet provides a way to customize text selection handles, be aware of its limitations and future compatibility issues. Always consider alternative approaches for long-term stability.</p>\n    <br>\n\n</body>\n</html>"));
		_customSelectionHandles(edittexttittle);
		_customSelectionHandles(edittext1);
	}
	
	public void _customSelectionHandles(final TextView _textview) {
		/*This snippet was created by InfLps.*/
		
		try {
			    //LEFT
				Field mTextSelectHandleLeftRes = TextView.class.getDeclaredField("mTextSelectHandleLeftRes");
				mTextSelectHandleLeftRes.setAccessible(true);
				mTextSelectHandleLeftRes.set(_textview, R.drawable.custom_select_handle_left);
				
				//RIGHT
				Field mTextSelectHandleRightRes = TextView.class.getDeclaredField("mTextSelectHandleRightRes");
				mTextSelectHandleRightRes.setAccessible(true);
				mTextSelectHandleRightRes.set(_textview, R.drawable.custom_select_handle_right);
				
				//MIDDLE
				Field mTextSelectHandleRes = TextView.class.getDeclaredField("mTextSelectHandleRes");
				mTextSelectHandleRes.setAccessible(true);
				mTextSelectHandleRes.set(_textview, R.drawable.custom_select_handle_middle);
				
			    //APPLYNG
				Field mEditor = TextView.class.getDeclaredField("mEditor");
				mEditor.setAccessible(true);
				Object editor = mEditor.get(_textview);
				Field mSelectHandleLeft = editor.getClass().getDeclaredField("mSelectHandleLeft");
				Field mSelectHandleRight = editor.getClass().getDeclaredField("mSelectHandleRight");
				Field mSelectHandleCenter = editor.getClass().getDeclaredField("mSelectHandleCenter");
				mSelectHandleLeft.setAccessible(true);
				mSelectHandleRight.setAccessible(true);
				mSelectHandleCenter.setAccessible(true);
				
				mSelectHandleLeft.set(editor, _textview.getContext().getResources().getDrawable(R.drawable.custom_select_handle_left, null));
				mSelectHandleRight.set(editor, _textview.getContext().getResources().getDrawable(R.drawable.custom_select_handle_right, null));
				mSelectHandleCenter.set(editor, _textview.getContext().getResources().getDrawable(R.drawable.custom_select_handle_middle, null));
		} catch (Exception e) {
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}