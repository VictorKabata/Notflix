package com.company.details.databinding;
import com.company.details.R;
import com.company.details.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMovieDetailsBindingImpl extends FragmentMovieDetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBar, 1);
        sViewsWithIds.put(R.id.imageView_back, 2);
        sViewsWithIds.put(R.id.imageView_favorite, 3);
        sViewsWithIds.put(R.id.fel_image_poster, 4);
        sViewsWithIds.put(R.id.imageView_movie_poster, 5);
        sViewsWithIds.put(R.id.constraint_layout_movie_metadata, 6);
        sViewsWithIds.put(R.id.textView_movie_release, 7);
        sViewsWithIds.put(R.id.view, 8);
        sViewsWithIds.put(R.id.textView_movie_duration, 9);
        sViewsWithIds.put(R.id.textView_movie_name, 10);
        sViewsWithIds.put(R.id.nested_scrollview_movie_details, 11);
        sViewsWithIds.put(R.id.constraint_popularity_rating, 12);
        sViewsWithIds.put(R.id.textView_movie_popularity, 13);
        sViewsWithIds.put(R.id.textView_movie_popularity_title, 14);
        sViewsWithIds.put(R.id.imageView_rating_star, 15);
        sViewsWithIds.put(R.id.textView_movie_rating, 16);
        sViewsWithIds.put(R.id.chipGroup_genres, 17);
        sViewsWithIds.put(R.id.textView_overview_title, 18);
        sViewsWithIds.put(R.id.textView_overview, 19);
        sViewsWithIds.put(R.id.textView_cast_title, 20);
        sViewsWithIds.put(R.id.recyclerview_cast, 21);
        sViewsWithIds.put(R.id.textView_trailer_title, 22);
        sViewsWithIds.put(R.id.cardView_trailer, 23);
        sViewsWithIds.put(R.id.imageView_video_placeholder, 24);
        sViewsWithIds.put(R.id.youtubePlayer_view, 25);
        sViewsWithIds.put(R.id.fab_play_trailer, 26);
        sViewsWithIds.put(R.id.textView_similar_movies_title, 27);
        sViewsWithIds.put(R.id.recyclerview_similarMovies, 28);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.motion.widget.MotionLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMovieDetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private FragmentMovieDetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[23]
            , (com.google.android.material.chip.ChipGroup) bindings[17]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[12]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[26]
            , (com.bosphere.fadingedgelayout.FadingEdgeLayout) bindings[4]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[24]
            , (androidx.core.widget.NestedScrollView) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (androidx.recyclerview.widget.RecyclerView) bindings[28]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[22]
            , (android.view.View) bindings[8]
            , (android.view.View) bindings[25]
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