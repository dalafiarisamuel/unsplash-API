object Dependencies {
    private const val kotlinStandardLibrary =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    private const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    private const val androidxLegacySupport =
        "androidx.legacy:legacy-support-v4:${Versions.androidxLegacySupport}"
    private const val androidxAnnotation =
        "androidx.annotation:annotation:${Versions.androidxAnnotation}"
    private const val androidXVectorDrawable =
        "androidx.vectordrawable:vectordrawable:${Versions.vectordrawable}"
    private const val kotlinCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutineVersion}"
    private const val kotlinCoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutineVersion}"
    private const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    private const val retrofitMoshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit2ConverterMoshi}"
    private const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiKotlin}"
    private const val ioCardAndroidSdk = "io.card:android-sdk:${Versions.ioCard}"
    private const val lifecycycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleComponent}"
    private const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleComponent}"
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navComponents}"
    private const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navComponents}"
    private const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleComponent}"
    private const val lifecycleCommonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleComponent}"
    private const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleComponent}"
    private const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleComponent}"
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    private const val javaxInject: String = "javax.inject:javax.inject:${Versions.javaxInject}"
    private const val hiltViewModel: String =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    private const val androidxActivityKtx =
        "androidx.activity:activity-ktx:${Versions.androidxActivityKtx}"
    private const val androidxFragmentKtx =
        "androidx.fragment:fragment-ktx:${Versions.androidxFragmentKtx}"
    private const val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltWorker}"
    private const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    private const val workManagerSupport = "androidx.work:work-gcm:${Versions.workManager}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    private const val retroMock = "co.infinum:retromock:${Versions.retroMock}"
    private const val pagingLibrary = "androidx.paging:paging-runtime:${Versions.pagingLibrary}"
    private const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    private const val androidxPreference = "androidx.preference:preference-ktx:${Versions.androidxPreference}"

    val implementations = listOf(
        kotlinStandardLibrary,
        ktx,
        androidxLegacySupport,
        androidxAnnotation,
        androidXVectorDrawable,
        kotlinCoroutineAndroid,
        kotlinCoroutineCore,
        retrofit2,
        retrofitMoshiConverter,
        moshiKotlin,
        ioCardAndroidSdk,
        lifecycycleViewModel,
        lifecycleRuntimeKtx,
        roomRuntime,
        roomKtx,
        navigationFragment,
        navigationUi,
        lifecycleRuntime,
        lifecycleCommonJava8,
        lifecycleViewModelKtx,
        lifecycleLiveData,
        hiltAndroid,
        javaxInject,
        hiltViewModel,
        androidxActivityKtx,
        androidxFragmentKtx,
        hiltWorker,
        workManager,
        workManagerSupport,
        loggingInterceptor,
        okHttp,
        retroMock,
        pagingLibrary,
        glide,
        androidxPreference
    )
}