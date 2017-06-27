package com.linknext.libgreatworks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.linknext.libgreatworks.ConstLib.DrawerType;
import com.linknext.libgreatworks.intro.IntroductionActivity;
import com.linknext.libgreatworks.util.ProcInstruction;
import com.linknext.libgreatworks.util.Utils;
import com.linknext.libopen.MyPref;
import com.linknext.libopen.Utl;
import com.linknext.libopen.UtlImage;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import static com.linknext.libgreatworks.ConstLib.DrawerType.Primary;
import static com.linknext.libgreatworks.ConstLib.DrawerType.Secondary;
import static com.linknext.libgreatworks.MainWorksActivity.DrawerInfo.Rate;
import static com.linknext.libopen.MyPref.readDefaultBoolean;

/**
 */
public class MainWorksActivity extends AppCompatActivity {

    static private Activity activity;
    private ProcMenu procMenu;
    private ProcGridView procGridView;
    private ProcDrawer procDrawer;
    private ProcInstruction procInstruction;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        MainWorksActivity.activity = this;
        procGridView = new ProcGridView( this );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Utl.setTranslucentNavitation( this );

        Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        initFab();

        procMenu = new ProcMenu();

        procGridView.init();

        procDrawer = new ProcDrawer( this );
        procDrawer.doit( savedInstanceState, toolbar );
        procDrawer.loadBackdrop();

        procInstruction = new ProcInstruction( this, R.id.instruction, R.layout.inst_main, ConstLib.kPref.InstDoNotShowMain );
        if( savedInstanceState == null ) {
            procInstruction.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu( Menu menu ) {
        procMenu.onPrepareOptionMenu( menu );
        return super.onPrepareOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if( procMenu.onOptionsItemSelected( item ) ) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onStart() {
        super.onStart();
        Utl.logDebug( "start.." );

        boolean funcRet = actionIntroduction( false );

        Utl.logDebug( "done." );
    }

    private Activity getActivity() {
        return activity;
    }

    private void actionDlgSearch() {

    }

    private void actionNewItem() {

    }

    private void actionFavoriteItem() {
    }

    private void actionReview() {
    }

    private boolean actionIntroduction( boolean force ) {
        Utl.logDebug( "start.. force=" + force );
        if( !force ) {
            boolean initialInstruction = readDefaultBoolean( Common.getCtx(), ConstLib.kPref.InitialInstruction.name(), false );
            if( initialInstruction ) {
                return false;
            }
        }
        startActivity( new Intent( getActivity(), IntroductionActivity.class ) );
        return false;
    }

    private void actionHelp( boolean force ) {

    }

    private void actionSetting() {
    }

    private void initFab() {
        final FloatingActionButton fab = (FloatingActionButton)findViewById( R.id.floating_action_button );
        fab.setImageDrawable( new IconicsDrawable( this, GoogleMaterial.Icon.gmd_search ).actionBar().color( Color.WHITE ) );
    }

    enum DrawerState {
        Expanded,
        Collapsed,
        Idle,;
    }

    enum DrawerInfo {
        Search( FontAwesome.Icon.faw_search, R.string.drawerItemSearch, R.string.drawerItemSearchDescription ),
        NewItems( FontAwesome.Icon.faw_lightbulb_o, R.string.drawerItemNew, R.string.drawerItemNewDescription ),
        FavoriteItems( FontAwesome.Icon.faw_thumbs_o_up, R.string.drawerItemFavorite, R.string.drawerItemFavoriteDescription ),
        SortDefault( FontAwesome.Icon.faw_sort, R.string.drawerItemSortDefault, R.string.drawerItemSortDefaultDescription ),
        SortNew( FontAwesome.Icon.faw_sort_amount_desc, R.string.drawerItemSortNew, R.string.drawerItemSortNewDescription ),
        SortRate( FontAwesome.Icon.faw_star_o, R.string.drawerItemSortRate, R.string.drawerItemSortRateDescription ),
        Update( FontAwesome.Icon.faw_cloud_download, R.string.drawerItemUpdate, R.string.drawerItemUpdateDescription ),
        PullRequest( FontAwesome.Icon.faw_cloud_upload, R.string.drawerItemPullRequest, R.string.drawerItemPullRequestDescription ),
        Rate( FontAwesome.Icon.faw_star, R.string.drawerItemRate, R.string.drawerItemRateDescription ),
        Intro( FontAwesome.Icon.faw_info, R.string.drawerItemIntro, R.string.drawerItemIntroDescription ),
        Help( FontAwesome.Icon.faw_question, R.string.drawer_item_help, R.string.drawerItemHelpDescription ),
        Setting( FontAwesome.Icon.faw_cog, R.string.drawer_item_settings, R.string.drawerItemSettingsDescription ),
        Test1( FontAwesome.Icon.faw_bug, R.string.labelTest, R.string.strEmpty ),
        Test2( FontAwesome.Icon.faw_bug, R.string.labelTest, R.string.strEmpty ),;

        int idTitle;
        int idDescription;
        FontAwesome.Icon idIcon;

        DrawerInfo( FontAwesome.Icon idIcon, int idTitle, int idDescription ) {
            this.idTitle = idTitle;
            this.idDescription = idDescription;
            this.idIcon = idIcon;
        }

        static public int size() {
            return values().length;
        }

        static public DrawerInfo getEnum( int id ) {
            if( id < 0 || id >= size() ) {
                return null;
            }
            return values()[id];
        }
    }

    class ProcGridView {

        private static final int INITIAL_DELAY_MILLIS = 300;
        private Activity activity;
        private GridView mGridView;

        public ProcGridView( Activity activity ) {
            this.activity = activity;
        }

        public void init() {

            mGridView = (GridView)findViewById( R.id.activity_gridview_gv );

            int div = getGridDivide( getActivity() );
            mGridView.setNumColumns( div );

            SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter( new MainListAdapter( getActivity() ) );
            swingBottomInAnimationAdapter.setAbsListView( mGridView );

            assert swingBottomInAnimationAdapter.getViewAnimator() != null;
            swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis( INITIAL_DELAY_MILLIS );

            mGridView.setAdapter( swingBottomInAnimationAdapter );

            adjustBottomOffset();
        }

        public void adjustBottomOffset() {
            Resources res = this.activity.getResources();
            int height = Utl.getNavigationBarHeight( res );
            adjustBottomOffset( height );
        }

        public void adjustBottomOffset( int val ) {
            int lVal = val;

            if( val <= 0 ) {
                Utl.logDebug( "val=" + val );
                lVal = 0;
            }
            if( Utl.isTranslucentNavigation( this.activity ) == false ) {
                Utl.logDebug( "GView NoTranslucent, so no need to set offset" );
                lVal = 0;
            }
            Utl.logDebug( "GView Offset=" + lVal );
            mGridView.setPadding( 0, 0, 0, lVal );
        }

        private int getGridDivide( Context ctx ) {

            DisplayMetrics displayMetrics = Utl.getDisplayMetrics( ctx );
            int wPx = displayMetrics.widthPixels;
            int hPx = displayMetrics.heightPixels;
            int wDp = (int)Utl.px2dp( ctx, wPx );
            int hDp = (int)Utl.px2dp( ctx, hPx );

            int div = 1;
            if( wDp <= 300 ) {
                div = 1;
            }
            else if( wDp <= 580 ) {
                div = 2;
            }
            else if( wDp <= 900 ) {
                div = 3;
            }
            else {
                div = 4;
            }

            String msg = "dimPx=" + wPx + "," + hPx + " dimDp=" + wDp + "," + hDp + " div=" + div;
            Utl.logDebug( msg );

            return div;
        }
    }

    class ProcDrawer {

        private Activity activity;
        private AccountHeader headerResult;
        private Drawer result;
        private AppBarLayout appBarLayout;
        private DrawerState state;

        ProcDrawer( Activity activity ) {
            this.activity = activity;
            this.appBarLayout = (AppBarLayout)findViewById( R.id.appbar );
            this.state = DrawerState.Idle;
        }

        private IDrawerItem createItem( DrawerInfo info, DrawerType type ) {
            return createItem( info, type, false );
        }

        private IDrawerItem createItem( DrawerInfo info, DrawerType type, boolean selectable ) {
            return createItem( info, type, null, selectable );
        }

        private IDrawerItem createItem( DrawerInfo info, DrawerType type, BadgeStyle badgeStyle, boolean selectable ) {
            IDrawerItem item;
            if( badgeStyle != null ) {
                if( type == Secondary ) {
                    item = new SecondaryDrawerItem()
                            .withName( info.idTitle )
                            .withDescription( info.idDescription )
                            .withIcon( info.idIcon )
                            .withIdentifier( info.ordinal() )
                            .withBadgeStyle( badgeStyle )
                            .withSelectable( selectable );
                }
                else {
                    item = new PrimaryDrawerItem()
                            .withName( info.idTitle )
                            .withDescription( info.idDescription )
                            .withIcon( info.idIcon )
                            .withIdentifier( info.ordinal() )
                            .withBadgeStyle( badgeStyle )
                            .withSelectable( selectable );
                }
            }
            else {
                if( type == Secondary ) {
                    item = new SecondaryDrawerItem()
                            .withName( info.idTitle )
                            .withDescription( info.idDescription )
                            .withIcon( info.idIcon )
                            .withIdentifier( info.ordinal() )
                            .withSelectable( selectable );
                }
                else {
                    item = new PrimaryDrawerItem()
                            .withName( info.idTitle )
                            .withDescription( info.idDescription )
                            .withIcon( info.idIcon )
                            .withIdentifier( info.ordinal() )
                            .withSelectable( selectable );

                }
            }
            return item;
        }

        private Intent createIntent( DrawerInfo info ) {
            return null;
        }

        void doit( Bundle savedInstanceState, Toolbar toolbar ) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById( R.id.collapsing_toolbar );
            collapsingToolbarLayout.setTitle( Common.getAppName() );

            appBarLayout.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged( AppBarLayout appBarLayout, int verticalOffset ) {
                    int totalScrollRange = appBarLayout.getTotalScrollRange();
                    int offset = Math.abs( verticalOffset );
                    if( offset == 0 ) {
                        onExpanded( totalScrollRange );
                    }
                    else if( offset >= totalScrollRange ) {
                        onCollapsed();
                    }
                    else {
                        state = DrawerState.Idle;
                    }
                }
            } );

            final IProfile profile = new ProfileDrawerItem().withName( getTitle2() );

            headerResult = new AccountHeaderBuilder()
                    .withActivity( this.activity )
                    .withCompactStyle( false )
                    .withHeaderBackground( getHeaderImage() )
                    .withSavedInstance( savedInstanceState )
                    .addProfiles( profile )
                    .withProfileImagesVisible( false )
                    .withSelectionListEnabled( false )
                    .build();

            result = new DrawerBuilder()
                    .withActivity( this.activity )
                    .withAccountHeader( headerResult )
                    .withToolbar( toolbar )
                    .withFullscreen( true )
                    .addDrawerItems(
                            createItem( DrawerInfo.Search, Primary ),
                            createItem( DrawerInfo.NewItems, Primary ),
                            createItem( DrawerInfo.FavoriteItems, Primary ),
                            new SectionDrawerItem().withName( R.string.drawerItemSortSection ),
                            createItem( DrawerInfo.SortDefault, Primary, true ),
                            createItem( DrawerInfo.SortNew, Primary, true ),
                            createItem( DrawerInfo.SortRate, Primary, true ),
                            new DividerDrawerItem(),
                            createItem( DrawerInfo.Update, Secondary, new BadgeStyle().withTextColor( Color.WHITE ).withColorRes( R.color.md_red_700 ), false ),
                            createItem( DrawerInfo.PullRequest, Secondary ),
                            new DividerDrawerItem(), createItem( Rate, Secondary ),
                            createItem( DrawerInfo.Intro, Secondary ),
                            createItem( DrawerInfo.Help, Secondary ) )
                    .withOnDrawerItemClickListener( new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick( View view, int position, IDrawerItem drawerItem ) {

                            if( drawerItem != null ) {

                                int id = (int)drawerItem.getIdentifier();
                                DrawerInfo info = DrawerInfo.getEnum( id );
                                switch( info ) {
                                case Search:
                                    actionDlgSearch();
                                    break;
                                case NewItems:
                                    actionNewItem();
                                    break;
                                case FavoriteItems:
                                    actionFavoriteItem();
                                    break;
                                case SortDefault:
                                    setSortType( result, Common.kSort.Default, false );
                                    break;
                                case SortNew:
                                    setSortType( result, Common.kSort.New, false );
                                    break;
                                case SortRate:
                                    setSortType( result, Common.kSort.Rate, false );
                                    break;
                                case Update:
                                    actionUpdateConfirm();
                                    break;
                                case PullRequest:
                                    actionPullRequest();
                                    break;
                                case Rate:
                                    actionReview();
                                    break;
                                case Intro:
                                    actionIntroduction( true );
                                    break;
                                case Help:
                                    actionHelp( true );
                                    break;
                                case Setting:
                                    actionSetting();
                                    break;
                                default:
                                    break;
                                }
                            }

                            return false;
                        }
                    } )
                    .withSavedInstance( savedInstanceState )
                    .withSelectedItem( -1 ).build();

            result.addStickyFooterItem( createItem( DrawerInfo.Setting, Primary ) );

            Common.kSort sortType = Utils.getSortType();
            setSortType( result, sortType, true );
        }

        private String getTitle2() {
            String title = Common.getAppName();
            String title2 = title.replace( ' ', '\n' );
            return title2;
        }

        /**
         * result.updateBadge( DrawerInfo.Update.ordinal(), new StringHolder(10 + ""));
         * result.updateBadge( DrawerInfo.Update.ordinal(), new StringHolder( ">" + 99));
         *
         * @param num
         */
        public void setBadgeNum( int num ) {

            Utl.logDebug( "" + num );

            StringHolder sh;
            if( num <= 0 ) {
                sh = null;
            }
            else if( num < 100 ) {
                sh = new StringHolder( num + "" );
            }
            else {
                sh = new StringHolder( ">" + 99 );
            }
            result.updateBadge( DrawerInfo.Update.ordinal(), sh );

            MyPref.saveDefaultInt( Common.getCtx(), ConstLib.kPref.AvailableUpdates.name(), num );
        }

        private void setSortType( Drawer drawer, Common.kSort sortType, boolean setSelection ) {
            if( setSelection ) {
                switch( sortType ) {
                case New:
                    drawer.setSelection( DrawerInfo.SortNew.ordinal() );
                    break;
                case Rate:
                    drawer.setSelection( DrawerInfo.SortRate.ordinal() );
                    break;
                default:
                    drawer.setSelection( DrawerInfo.SortDefault.ordinal() );
                    break;
                }
            }
            MyPref.saveDefaultInt( this.activity, ConstLib.kPref.SortType.name(), sortType.ordinal() );
        }

        private void actionUpdateConfirm() {
        }

        private void actionPullRequest() {
        }

        /**
         * DB Update
         */
        private void actionUpdateDB() {
        }

        private void onExpanded( int totalScrollRange ) {
        }

        private void onCollapsed() {
        }

        private boolean isDisableOffset() {
            Resources res = this.activity.getResources();
            if( Utl.getNavigationBarHeight( res ) <= 0 ) {
                return true;
            }
            if( Utl.isTranslucentNavigation( this.activity ) ) {
                return false;
            }
            return false;
        }

        void loadBackdrop() {
            Resources res = this.activity.getResources();
            final ImageView imageView = (ImageView)findViewById( R.id.backdrop );
            Drawable drawable = UtlImage.getDrawable( res, R.drawable.img_front2 );
            imageView.setImageDrawable( drawable );
        }

        private int getHeaderImage() {
            int drawer = R.drawable.img_header_moon2;
            return drawer;
        }

    }

    class ProcMenu {

        private MenuItem itemSearch;

        void onPrepareOptionMenu( Menu menu ) {
            itemSearch = menu.findItem( R.id.menuSearch );
        }

        public boolean onOptionsItemSelected( MenuItem item ) {
            int id = item.getItemId();
            if( id == R.id.menuSearch ) {
                actionDlgSearch();
                return true;
            }
            return false;
        }

        void onStateChanged( DrawerState state ) {
            switch( state ) {
            case Expanded:
                if( itemSearch != null ) {
                    itemSearch.setVisible( false );
                }
                break;
            case Collapsed:
                if( itemSearch != null ) {
                    itemSearch.setVisible( true );
                }
                break;
            default:
                break;
            }
        }
    }

}
