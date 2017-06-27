/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linknext.libgreatworks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linknext.libgreatworks.Common.kCategory;
import com.linknext.libopen.Utl;
import com.nhaarman.listviewanimations.ArrayAdapter;

public class MainListAdapter extends ArrayAdapter<Integer> {

    private final Context mContext;

    public MainListAdapter( final Context context ) {
        mContext = context;
    }

    @Override
    public View getView( final int position, final View convertView, final ViewGroup parent ) {
        return getView2( position, convertView, parent );
    }

    private View getView2( final int position, final View convertView, final ViewGroup parent ) {

        ViewHolder holder;
        View view = convertView;
        if( view == null ) {
            view = LayoutInflater.from( mContext ).inflate( R.layout.list_category, parent, false );

            holder = new ViewHolder();
            holder.title = (TextView)view.findViewById( R.id.textCategory );
            holder.imageView = (ImageView)view.findViewById( R.id.imageView );

            view.setTag( holder );
        }
        else {
            holder = (ViewHolder)view.getTag();
        }


        kCategory cate = kCategory.getEnum( position );

        holder.title.setText( cate.name() );

        int imageResId = R.drawable.img_nature1;
        imageResId = cate.getIdImg();

        ImageView iv = holder.imageView;

        Bitmap bitmap = BitmapFactory.decodeResource( mContext.getResources(), imageResId );
        iv.setImageBitmap( bitmap );

        iv.setClickable( true );
        iv.setTag( cate );
        iv.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick( View v ) {
                kCategory category = (kCategory)v.getTag();
                actionImgClicked( category );

            }
        } );

        return view;
    }


    public int getCount() {
        return kCategory.size;
    }

    private void actionImgClicked( kCategory category ) {
        Utl.logDebug( category.name() );
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView title;
    }


}
