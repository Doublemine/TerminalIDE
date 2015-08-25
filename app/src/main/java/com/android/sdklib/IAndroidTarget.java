/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.sdklib;

import java.util.Map;


/**
 * A version of Android that applications can target when building.
 */
public interface IAndroidTarget extends Comparable<IAndroidTarget> {

    /**
     * OS Path to the "android.jar" file.
     */
    int ANDROID_JAR = 1;
    /**
     * OS Path to the "framework.aidl" file.
     */
    int ANDROID_AIDL = 2;
    /**
     * OS Path to "images" folder which contains the emulator system images.
     */
    int IMAGES = 3;
    /**
     * OS Path to the "samples" folder which contains sample projects.
     */
    int SAMPLES = 4;
    /**
     * OS Path to the "skins" folder which contains the emulator skins.
     */
    int SKINS = 5;
    /**
     * OS Path to the "templates" folder which contains the templates for new projects.
     */
    int TEMPLATES = 6;
    /**
     * OS Path to the "data" folder which contains data & libraries for the SDK tools.
     */
    int DATA = 7;
    /**
     * OS Path to the "attrs.xml" file.
     */
    int ATTRIBUTES = 8;
    /**
     * OS Path to the "attrs_manifest.xml" file.
     */
    int MANIFEST_ATTRIBUTES = 9;
    /**
     * OS Path to the "data/layoutlib.jar" library.
     */
    int LAYOUT_LIB = 10;
    /**
     * OS Path to the "data/res" folder.
     */
    int RESOURCES = 11;
    /**
     * OS Path to the "data/fonts" folder.
     */
    int FONTS = 12;
    /**
     * OS Path to the "data/widgets.txt" file.
     */
    int WIDGETS = 13;
    /**
     * OS Path to the "data/activity_actions.txt" file.
     */
    int ACTIONS_ACTIVITY = 14;
    /**
     * OS Path to the "data/broadcast_actions.txt" file.
     */
    int ACTIONS_BROADCAST = 15;
    /**
     * OS Path to the "data/service_actions.txt" file.
     */
    int ACTIONS_SERVICE = 16;
    /**
     * OS Path to the "data/categories.txt" file.
     */
    int CATEGORIES = 17;
    /**
     * OS Path to the "sources" folder.
     */
    int SOURCES = 18;
    /**
     * OS Path to the target specific docs
     */
    int DOCS = 19;
    /**
     * OS Path to the target's version of the aapt tool.
     */
    int AAPT = 20;
    /**
     * OS Path to the target's version of the aidl tool.
     */
    int AIDL = 21;
    /**
     * OS Path to the target's version of the dx too.
     */
    int DX = 22;
    /**
     * OS Path to the target's version of the dx.jar file.
     */
    int DX_JAR = 23;
    /**
     * OS Path to the "ant" folder which contains the ant build rules (ver 2 and above)
     */
    int ANT = 24;

    /**
     * Return value for {@link #getUsbVendorId()} meaning no USB vendor IDs are defined by the
     * Android target.
     */
    int NO_USB_ID = 0;

    /**
     * An optional library provided by an Android Target
     */
    interface IOptionalLibrary {
        /**
         * The name of the library, as used in the manifest (&lt;uses-library&gt;).
         */
        String getName();

        /**
         * The file name of the jar file.
         */
        String getJarName();

        /**
         * Absolute OS path to the jar file.
         */
        String getJarPath();

        /**
         * Description of the library.
         */
        String getDescription();
    }

    /**
     * Returns the target location.
     */
    String getLocation();

    /**
     * Returns the name of the vendor of the target.
     */
    String getVendor();

    /**
     * Returns the name of the target.
     */
    String getName();

    /**
     * Returns the full name of the target, possibly including vendor name.
     */
    String getFullName();

    /**
     * Returns the name to be displayed when representing all the libraries this target contains.
     */
    String getClasspathName();

    /**
     * Returns the description of the target.
     */
    String getDescription();

    /**
     * Returns the version of the target. This is guaranteed to be non-null.
     */
    AndroidVersion getVersion();

    /**
     * Returns the platform version as a readable string.
     */
    String getVersionName();

    /**
     * Returns the revision number for the target.
     */
    int getRevision();

    /**
     * Returns true if the target is a standard Android platform.
     */
    boolean isPlatform();

    /**
     * Returns the parent target. This is likely to only be non <code>null</code> if
     * {@link #isPlatform()} returns <code>false</code>
     */
    IAndroidTarget getParent();

    /**
     * Returns the path of a platform component.
     *
     * @param pathId the id representing the path to return. Any of the constants defined in the
     *               {@link IAndroidTarget} interface can be used.
     */
    String getPath(int pathId);

    /**
     * Returns the available skins for this target.
     */
    String[] getSkins();

    /**
     * Returns the default skin for this target.
     */
    String getDefaultSkin();

    /**
     * Returns the available optional libraries for this target.
     *
     * @return an array of optional libraries or <code>null</code> if there is none.
     */
    IOptionalLibrary[] getOptionalLibraries();

    /**
     * Returns the list of libraries available for a given platform.
     *
     * @return an array of libraries provided by the platform or <code>null</code> if there is none.
     */
    String[] getPlatformLibraries();

    /**
     * Return the value of a given property for this target.
     *
     * @return the property value or <code>null</code> if it was not found.
     */
    String getProperty(String name);

    /**
     * Returns the value of a given property for this target as an Integer value.
     * <p/> If the value is missing or is not an integer, the method will return the given default
     * value.
     *
     * @param name         the name of the property to return
     * @param defaultValue the default value to return.
     * @see Integer#decode(String)
     */
    Integer getProperty(String name, Integer defaultValue);

    /**
     * Returns the value of a given property for this target as a Boolean value.
     * <p/> If the value is missing or is not an boolean, the method will return the given default
     * value.
     *
     * @param name         the name of the property to return
     * @param defaultValue the default value to return.
     * @see Boolean#valueOf(String)
     */

    Boolean getProperty(String name, Boolean defaultValue);

    /**
     * Returns all the properties associated with this target. This can be null if the target has
     * no properties.
     */
    Map<String, String> getProperties();

    /**
     * Returns the USB Vendor ID for the vendor of this target.
     * <p/>If the target defines no USB Vendor ID, then the method return 0.
     */
    int getUsbVendorId();

    /**
     * Returns whether the given target is compatible with the receiver.
     * <p/>
     * This means that a project using the receiver's target can run on the given target.
     * <br/>
     * Example:
     * <pre>
     * CupcakeTarget.canRunOn(DonutTarget) == true
     * </pre>.
     *
     * @param target the IAndroidTarget to test.
     */
    boolean canRunOn(IAndroidTarget target);

    /**
     * Returns a string able to uniquely identify a target.
     * Typically the target will encode information such as api level, whether it's a platform
     * or add-on, and if it's an add-on vendor and add-on name.
     */
    String hashString();
}
