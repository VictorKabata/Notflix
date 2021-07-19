package com.company.home;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.company.home.databinding.FragmentHomeBindingImpl;
import com.company.home.databinding.ItemHomeViewpagerBindingImpl;
import com.company.home.databinding.ItemPopularShowBindingImpl;
import com.company.home.databinding.ItemTopRatedShowBindingImpl;
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
  private static final int LAYOUT_FRAGMENTHOME = 1;

  private static final int LAYOUT_ITEMHOMEVIEWPAGER = 2;

  private static final int LAYOUT_ITEMPOPULARSHOW = 3;

  private static final int LAYOUT_ITEMTOPRATEDSHOW = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.home.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.home.R.layout.item_home_viewpager, LAYOUT_ITEMHOMEVIEWPAGER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.home.R.layout.item_popular_show, LAYOUT_ITEMPOPULARSHOW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.company.home.R.layout.item_top_rated_show, LAYOUT_ITEMTOPRATEDSHOW);
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
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMHOMEVIEWPAGER: {
          if ("layout/item_home_viewpager_0".equals(tag)) {
            return new ItemHomeViewpagerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_home_viewpager is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPOPULARSHOW: {
          if ("layout/item_popular_show_0".equals(tag)) {
            return new ItemPopularShowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_popular_show is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTOPRATEDSHOW: {
          if ("layout/item_top_rated_show_0".equals(tag)) {
            return new ItemTopRatedShowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_top_rated_show is invalid. Received: " + tag);
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
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/fragment_home_0", com.company.home.R.layout.fragment_home);
      sKeys.put("layout/item_home_viewpager_0", com.company.home.R.layout.item_home_viewpager);
      sKeys.put("layout/item_popular_show_0", com.company.home.R.layout.item_popular_show);
      sKeys.put("layout/item_top_rated_show_0", com.company.home.R.layout.item_top_rated_show);
    }
  }
}
