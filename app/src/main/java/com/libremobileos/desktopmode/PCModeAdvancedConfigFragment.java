package com.libremobileos.desktopmode;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class PCModeAdvancedConfigFragment extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener{

    public static final String KEY_PC_MODE_EMULATE_TOUCH = "pc_mode_emulate_touch";
    public static final String KEY_PC_MODE_RELATIVE_INPUT = "pc_mode_relative_input";
    public static final String KEY_PC_MODE_MIRROR_INTERNAL = "pc_mode_mirror_internal";
    public static final String KEY_PC_MODE_AUDIO = "pc_mode_audio";
    public static final String KEY_PC_MODE_REMOTE_CURSOR = "pc_mode_remote_cursor";
    public static final String KEY_PC_MODE_CLIPBOARD = "pc_mode_clipboard";

    protected SharedPreferences mSharedPreferences;
    protected Boolean mEmulateTouchValue;
    protected Boolean mRelativeInputValue;
    protected Boolean mMirrorInternalValue;
    protected Boolean mAudioValue;
    protected Boolean mRemoteCursorValue;
    protected Boolean mClipboardValue;

    protected SwitchPreferenceCompat pcModeEmulateTouch;
    protected SwitchPreferenceCompat pcModeRelativeInput;
    protected SwitchPreferenceCompat pcModeMirrorInternal;
    protected SwitchPreferenceCompat pcModeAudio;
    protected SwitchPreferenceCompat pcModeRemoteCursor;
    protected SwitchPreferenceCompat pcModeClipboard;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pc_mode_advanced_config_preferences, rootKey);

        mSharedPreferences = requireActivity().getSharedPreferences("PCModeConfigs", MODE_PRIVATE);
        mEmulateTouchValue = mSharedPreferences.getBoolean(KEY_PC_MODE_EMULATE_TOUCH, false);
        mRelativeInputValue = mSharedPreferences.getBoolean(KEY_PC_MODE_RELATIVE_INPUT, false);
        mMirrorInternalValue = mSharedPreferences.getBoolean(KEY_PC_MODE_MIRROR_INTERNAL, false);
        mAudioValue = mSharedPreferences.getBoolean(KEY_PC_MODE_AUDIO, true);
        mRemoteCursorValue = mSharedPreferences.getBoolean(KEY_PC_MODE_REMOTE_CURSOR, true);
        mClipboardValue = mSharedPreferences.getBoolean(KEY_PC_MODE_CLIPBOARD, true);

        pcModeEmulateTouch = findPreference(KEY_PC_MODE_EMULATE_TOUCH);
        pcModeRelativeInput = findPreference(KEY_PC_MODE_RELATIVE_INPUT);
        pcModeMirrorInternal = findPreference(KEY_PC_MODE_MIRROR_INTERNAL);
        pcModeAudio = findPreference(KEY_PC_MODE_AUDIO);
        pcModeRemoteCursor = findPreference(KEY_PC_MODE_REMOTE_CURSOR);
        pcModeClipboard = findPreference(KEY_PC_MODE_CLIPBOARD);

        pcModeEmulateTouch.setOnPreferenceChangeListener(this);
        pcModeRelativeInput.setOnPreferenceChangeListener(this);
        pcModeMirrorInternal.setOnPreferenceChangeListener(this);
        pcModeAudio.setOnPreferenceChangeListener(this);
        pcModeRemoteCursor.setOnPreferenceChangeListener(this);
        pcModeClipboard.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference pref, Object newValue) {
        Boolean isChecked = (Boolean) newValue;
        switch (pref.getKey()) {
            case KEY_PC_MODE_EMULATE_TOUCH:
                if (isChecked != mEmulateTouchValue) {
                    mEmulateTouchValue = isChecked;
                    applyChanges();
                }
                return true;
            case KEY_PC_MODE_RELATIVE_INPUT:
                if (isChecked != mRelativeInputValue) {
                    mRelativeInputValue = isChecked;
                    applyChanges();
                }
                return true;
            case KEY_PC_MODE_MIRROR_INTERNAL:
                if (isChecked != mMirrorInternalValue) {
                    mMirrorInternalValue = isChecked;
                    applyChanges();
                }
                return true;
            case KEY_PC_MODE_AUDIO:
                if (isChecked != mAudioValue) {
                    mAudioValue = isChecked;
                    applyChanges();
                }
                return true;
            case KEY_PC_MODE_REMOTE_CURSOR:
                if (isChecked != mRemoteCursorValue) {
                    mRemoteCursorValue = isChecked;
                    applyChanges();
                }
                return true;
            case KEY_PC_MODE_CLIPBOARD:
                if (isChecked != mClipboardValue) {
                    mClipboardValue = isChecked;
                    applyChanges();
                }
                return true;
        }

        return false;
    }

    private void applyChanges() {
        SharedPreferences.Editor myEdit = mSharedPreferences.edit();

        myEdit.putBoolean(KEY_PC_MODE_EMULATE_TOUCH, mEmulateTouchValue);
        myEdit.putBoolean(KEY_PC_MODE_RELATIVE_INPUT, mRelativeInputValue);
        myEdit.putBoolean(KEY_PC_MODE_MIRROR_INTERNAL, mMirrorInternalValue);
        myEdit.putBoolean(KEY_PC_MODE_AUDIO, mAudioValue);
        myEdit.putBoolean(KEY_PC_MODE_REMOTE_CURSOR, mRemoteCursorValue);
        myEdit.putBoolean(KEY_PC_MODE_CLIPBOARD, mClipboardValue);

        myEdit.apply();
    }
}
