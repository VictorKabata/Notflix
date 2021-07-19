package com.company.details;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.company.details.databinding.FragmentMovieDetailsBindingImpl;
import com.company.details.databinding.ItemCastBindingImpl;
import com.company.details.databinding.ItemSimilarShowBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTMOVIEDETAILS = 1;

  private static final int LAYOUT_ITEMCAST = 2;

  private static final int LAYOUT_ITEMSIMILARSHOW = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.details.R.layout.fragment_movie_details, LAYOUT_FRAGMENTMOVIEDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.details.R.layout.item_cast, LAYOUT_ITEMCAST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.details.R.layout.item_similar_show, LAYOUT_ITEMSIMILARSHOW);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTMOVIEDETAILS: {
          if ("layout/fragment_movie_details_0".equals(tag)) {
            return new FragmentMovieDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_movie_details is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCAST: {
          if ("layout/item_cast_0".equals(tag)) {
            return new ItemCastBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_cast is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSIMILARSHOW: {
          if ("layout/item_similar_show_0".equals(tag)) {
            return new ItemSimilarShowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_similar_show is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(0);
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/fragment_movie_details_0", com.company.details.R.layout.fragment_movie_details);
      sKeys.put("layout/item_cast_0", com.company.details.R.layout.item_cast);
      sKeys.put("layout/item_similar_show_0", com.company.details.R.layout.item_similar_show);
    }
  }
}
