package com.linknext.libgreatworks;

/**
 */
public interface ConstLib {

    enum kIntentKey {
        Category,
        SearchKey,
        SortType,
        DBFileName,
        DBVersion,
        DBUserFileName,
        DBUserVersion,
        DrawableId,
        Url,
        JavaScript,
        NumUpdates,
        InstructionContainer,
        InstructionLayout,
        InstructionPref,;
    }

    enum DrawerType {
        Primary,
        Secondary,;
    }

    enum kPref {
        UsageNumClick,
        UsageCalenInstalled,
        SortType,
        FamLabel,
        AwsAccess,
        AwsSecret,
        EbayAppId,
        EbayTrackingId,
        ThemeApp,
        HelpReview,
        HelpFeedback,
        HelpUpdateInfo,
        HelpAcknowledgment,
        HelpAbout,
        UpdateInfoDoNotShow,
        AppVersionCode,
        InitialInstruction,
        ScreenLock,
        AvailableUpdates,
        RegisterLatest,
        DBUpdate,
        InstDoNotShowMain,
        InstDoNotShowCards,
        InstDoNotShowGifView,;
    }

    enum kOrientation {
        Auto,
        Portrait,
        Landscape;

        public static int size = values().length;

        public static kOrientation getEnum( int i ) {
            if( i >= size ) {
                i = 0;
            }
            return values()[i];
        }
    }


}
