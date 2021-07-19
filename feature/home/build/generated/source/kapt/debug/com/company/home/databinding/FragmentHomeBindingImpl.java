package com.company.home.databinding;
import com.company.home.R;
import com.company.home.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView_homeLogo, 1);
        sViewsWithIds.put(R.id.viewPager_trendingShows, 2);
        sViewsWithIds.put(R.id.dots_trendingShows, 3);
        sViewsWithIds.put(R.id.nestedScrollView_container, 4);
        sViewsWithIds.put(R.id.textView_popularMovies, 5);
        sViewsWithIds.put(R.id.textView_seeMorePopularMovies, 6);
        sViewsWithIds.put(R.id.recyclerview_popularMovies, 7);
        sViewsWithIds.put(R.id.textView_topRatedMovies, 8);
        sViewsWithIds.put(R.id.textView_seeTopRatedMovies, 9);
        sViewsWithIds.put(R.id.recyclerview_topRatedMovies, 10);
        sViewsWithIds.put(R.id.textView_popularTvShows, 11);
        sViewsWithIds.put(R.id.textView_seeMorePopularTvShows, 12);
        sViewsWithIds.put(R.id.recyclerview_popularTvShows, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.motion.widget.MotionLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.make.dots.dotsindicator.DotsIndicator) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (androidx.core.widget.NestedScrollView) bindings[4]
            , (androidx.recyclerview.widget.RecyclerView) bindings[7]
            , (androidx.recyclerview.widget.RecyclerView) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[8]
            , (androidx.viewpager.widget.ViewPager) bindings[2]
            );
        this.mboundView0 = (androidx.constraintlayout.motion.widget.MotionLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}