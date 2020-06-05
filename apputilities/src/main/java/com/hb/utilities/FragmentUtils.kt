package com.hb.utilities

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.CheckResult
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

object FragmentUtils {

    private fun FragmentUtils() {
        throw RuntimeException("Unable to instantiate class " + javaClass.canonicalName)
    }

    /**
     * Replaces existing fragment with a [Fragment], that gets instantiated from `clazz`
     * with optional `fragmentBundle` attached.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param clazz          Fragment's class that should be instantiated
     * @param fragmentBundle This bundle gets supplied to the fragment
     * @param addToBackStack If true transaction would be added to back stack.
     * @param tag            Fragment transaction's tag
     */
    fun replace(
        activity: AppCompatActivity, @IdRes frameResId: Int, clazz: Class<*>,
        fragmentBundle: Bundle?, addToBackStack: Boolean, tag: String?
    ) {
        val ft =
            activity.supportFragmentManager.beginTransaction()
        ft.replace(
            frameResId,
            Fragment.instantiate(activity, clazz.name, fragmentBundle),
            tag
        )
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }

    /**
     * Replaces existing fragment with `fragment`.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param fragment       Fragment instance
     * @param addToBackStack If true transaction would be added to back stack.
     * @param tag            Fragment transaction's tag
     */
    fun replace(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val ft =
            activity.supportFragmentManager.beginTransaction()
        ft.replace(frameResId, fragment!!, tag)
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }

    /**
     * Replaces existing fragment with `fragment` applying animation.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param fragment       Fragment instance
     * @param addToBackStack If true transaction would be added to back stack.
     * @param tag            Fragment transaction's tag
     */
    fun replaceWithAnim(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean,
        tag: String?,
        @AnimRes animIn: Int,
        @AnimRes animOut: Int
    ) {
        val ft =
            activity.supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(animIn, animOut)
        ft.replace(frameResId, fragment!!, tag)
        if (addToBackStack) ft.addToBackStack(null)
        ft.commitAllowingStateLoss()
    }

    /**
     * Instantiates a fragment from `clazz` with optional `bundle` supplied, and adds
     * the fragment to `frameResId`.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param clazz          Fragment's class that should be instantiated
     * @param bundle         This bundle gets supplied to the fragment
     * @param addToBackStack If true transaction would be added to back stack.
     * @param tag            Fragment transaction's tag
     */
    fun add(
        activity: AppCompatActivity, @IdRes frameResId: Int, clazz: Class<*>,
        bundle: Bundle?, addToBackStack: Boolean, tag: String?
    ) {
        val ft =
            activity.supportFragmentManager.beginTransaction()
        ft.add(
            frameResId,
            Fragment.instantiate(activity, clazz.name, bundle),
            tag
        )
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }

    /**
     * Adds provided `fragment` to `frameResId`.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param fragment       Fragment instance
     * @param addToBackStack If true transaction would be added to back stack.
     * @param tag            Fragment transaction's tag
     */
    fun add(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val ft =
            activity.supportFragmentManager.beginTransaction()
        ft.add(frameResId, fragment!!, tag)
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }

    /**
     * Adds provided `fragment` to `frameResId`.
     *
     * @param activity       Hosting activity
     * @param frameResId     Layout id where fragment should be placed
     * @param fragment       Fragment instance
     * @param addToBackStack If true transaction would be added to back stack.
     */
    fun add(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean
    ) {
        add(
            activity,
            frameResId,
            fragment,
            addToBackStack,
            null
        )
    }

    /**
     * Adds provided `fragment` to `frameResId`.
     *
     * @param activity   Hosting activity
     * @param frameResId Layout id where fragment should be placed
     * @param fragment   Fragment instance
     */
    fun add(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?
    ) {
        add(
            activity,
            frameResId,
            fragment,
            false,
            null
        )
    }

    /**
     * Adds provided `fragment` to `frameResId` without adding to backstack.
     *
     * @param activity   Hosting activity
     * @param frameResId id where fragment should be placed
     * @param fragment   Fragment instance
     * @param tag        The tag, that should be applied to transaction.
     */
    fun add(
        activity: AppCompatActivity,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        tag: String?
    ) {
        add(
            activity,
            frameResId,
            fragment,
            false,
            tag
        )
    }

    /**
     * Adds provided `fragment` to `frameResId` without adding to backstack.
     *
     * @param activity Hosting activity
     * @param fragment Fragment instance
     * @param tag      The tag, that should be applied to transaction.
     */
    fun add(
        activity: AppCompatActivity,
        fragment: Fragment?,
        tag: String?
    ) {
        add(activity, 0, fragment, false, tag)
    }

    /**
     * Pops off the last fragment in the provided activity's stack.
     *
     * @param activity The activity which fragment's stack will be popped
     */
    fun pop(activity: AppCompatActivity) {
        activity.supportFragmentManager.popBackStack()
    }

    /**
     * Add `fragment` as a child to `rootFragment`.
     */
    fun addChild(
        rootFragment: Fragment,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val ft =
            rootFragment.childFragmentManager.beginTransaction()
        ft.add(frameResId, fragment!!, tag)
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }


    fun replaceChild(
        rootFragment: Fragment,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val ft =
            rootFragment.childFragmentManager.beginTransaction()
        ft.replace(frameResId, fragment!!, tag)
        if (addToBackStack) ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commitAllowingStateLoss()
    }

    /**
     * Searches for child fragment in `fragment` by provided `tag`.
     *
     * @param fragment Fragment to search in.
     * @param tag      The tag, to perform search by.
     * @return The [Fragment] if there's a such fragment in [FragmentManager].
     * Null otherwise.
     */
    @CheckResult
    fun <T : Fragment?> findChildByTag(
        fragment: Fragment,
        tag: String
    ): T? {
        return fragment.childFragmentManager.findFragmentByTag(tag) as T?
    }

    /**
     * Add `fragment` as a child to `rootFragment`.
     */
    fun addChild(
        rootFragment: Fragment,
        @IdRes frameResId: Int,
        fragment: Fragment?,
        tag: String?
    ) {
        addChild(
            rootFragment,
            frameResId,
            fragment,
            false,
            tag
        )
    }

    /**
     * Searches for fragment in [FragmentManager] by provided `tag`.
     *
     * @param activity Activity to search in.
     * @param tag      The tag, to perform search by.
     * @return The [Fragment] if there's a such fragment in [FragmentManager].
     * Null otherwise.
     */
    @CheckResult
    fun <T : Fragment?> findByTag(
        activity: AppCompatActivity,
        tag: String
    ): T? {
        return activity.supportFragmentManager.findFragmentByTag(tag) as T?
    }

    /**
     * Searches for fragment in [FragmentManager] by provided `id`.
     *
     * @param activity Activity to search in.
     * @param id       The id, to perform search by.
     * @return The [Fragment] if there's a such fragment in [FragmentManager].
     * Null otherwise.
     */
    @CheckResult
    fun <T : Fragment?> findById(
        activity: AppCompatActivity,
        @IdRes id: Int
    ): T? {
        return activity.supportFragmentManager.findFragmentById(id) as T?
    }

    /**
     * Display the dialog, adding the fragment to the given FragmentManager.  This
     * is a convenience for explicitly creating a transaction, adding the
     * fragment to it with the given tag, and committing it.  This does
     * *not* add the transaction to the back stack.  When the fragment
     * is dismissed, a new transaction will be executed to remove it from
     * the activity.
     *
     * @param activity The Activity where the fragment should be shown
     * @param tag      The tag for this fragment, as per
     * [FragmentTransaction.add].
     */
    fun showDialog(
        activity: AppCompatActivity,
        dialogFragment: DialogFragment,
        tag: String?
    ) {
        dialogFragment.show(activity.supportFragmentManager, tag)
    }

    /**
     * Display the dialog, adding the fragment to the given FragmentManager.  This
     * is a convenience for explicitly creating a transaction, adding the
     * fragment to it with the given tag, and committing it.  This does
     * *not* add the transaction to the back stack.  When the fragment
     * is dismissed, a new transaction will be executed to remove it from
     * the activity.
     *
     * @param activity The Activity where the fragment should be shown
     */
    fun showDialog(
        activity: AppCompatActivity,
        dialogFragment: DialogFragment
    ) {
        showDialog(
            activity,
            dialogFragment,
            null
        )
    }

    /**
     * Removes fragment with tag **`tag`** from `activity`'s fragment manager if there
     * is such fragment.
     *
     * @param activity Hosting activity.
     * @param tag      The tag of the fragment.
     * @return True  if fragment is removed. False otherwise.
     */
    fun removeByTag(activity: AppCompatActivity, tag: String): Boolean {
        val fr =
            findByTag<Fragment>(activity, tag)
        if (null != fr) {
            activity.supportFragmentManager.beginTransaction()
                .remove(fr)
                .commit()
            return true
        }
        return false
    }

    /**
     * Removes fragment with id **`id`** from `activity`'s fragment manager if there
     * is such fragment.
     *
     * @param activity Hosting activity.
     * @param id       The id of the fragment.
     * @return True  if fragment is removed. False otherwise.
     */
    fun removeById(activity: AppCompatActivity, @IdRes id: Int): Boolean {
        val fr =
            findById<Fragment>(activity, id)
        if (null != fr) {
            activity.supportFragmentManager.beginTransaction()
                .remove(fr)
                .commit()
            return true
        }
        return false
    }

    /**
     * Shows the `fragment`.
     */
    fun show(
        activity: AppCompatActivity,
        fragment: Fragment?
    ) {
        activity.supportFragmentManager.beginTransaction()
            .show(fragment!!)
            .commit()
    }

    /**
     * Hides the `fragment`.
     */
    fun hide(
        activity: AppCompatActivity,
        fragment: Fragment?
    ) {
        activity.supportFragmentManager.beginTransaction()
            .hide(fragment!!)
            .commit()
    }

    /**
     * Hides the `hide` fragment and shows the `show` fragment.
     */
    fun hideAndShow(
        activity: AppCompatActivity,
        hide: Fragment?,
        show: Fragment?
    ) {
        activity.supportFragmentManager.beginTransaction()
            .hide(hide!!)
            .show(show!!)
            .commit()
    }

    /**
     * Hides the `hide` fragment and shows the `show` fragment with animations.
     */
    fun hideAndShowWithAnimation(
        activity: AppCompatActivity,
        hide: Fragment?,
        show: Fragment?,
        @AnimRes animIn: Int,
        @AnimRes animOut: Int
    ) {
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(animIn, animOut)
            .hide(hide!!)
            .show(show!!)
            .commit()
    }
}