<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="NavAction">
        <attr name="android:id"/>
        <attr format="reference" name="destination"/>
        <attr format="boolean" name="launchSingleTop"/>
        <attr format="boolean" name="restoreState"/>
        <attr format="reference" name="popUpTo"/>
        <attr format="boolean" name="popUpToInclusive"/>
        <attr format="boolean" name="popUpToSaveState"/>
        <attr format="reference" name="enterAnim"/>
        <attr format="reference" name="exitAnim"/>
        <attr format="reference" name="popEnterAnim"/>
        <attr format="reference" name="popExitAnim"/>
    </declare-styleable>
    <declare-styleable name="NavArgument">
        <attr name="android:name"/>
        <attr name="android:defaultValue"/>
        <attr format="boolean" name="nullable"/>
        <attr format="string" name="argType"/>
    </declare-styleable>
    <declare-styleable name="NavDeepLink">
        <attr format="string" name="uri"/>
        <attr format="string" name="action"/>
        <attr format="string" name="mimeType"/>
        <attr name="android:autoVerify"/>
    </declare-styleable>
    <declare-styleable name="NavGraphNavigator">
        <attr format="reference" name="startDestination"/>
    </declare-styleable>
    <declare-styleable name="Navigator">
        <attr name="android:id"/>
        <attr format="string" name="route"/>
        <attr name="android:label"/>
    </declare-styleable>
</resources>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                