package org.robolectric.shadows;

import android.os.Build.VERSION_CODES;
import android.view.translation.TranslationCapability;
import android.view.translation.TranslationManager;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import java.util.Set;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.Resetter;

/** Shadow for {@link TranslationManager}. */
@Implements(
    value = TranslationManager.class,
    minSdk = VERSION_CODES.S,
    isInAndroidSdk = false // turn off shadowOf generation
    )
public class ShadowTranslationManager {
  private static final Table<Integer, Integer, ImmutableSet<TranslationCapability>>
      onDeviceTranslationCapabilities = HashBasedTable.create();

  @Implementation
  protected Set<TranslationCapability> getOnDeviceTranslationCapabilities(
      int sourceFormat, int targetFormat) {
    if (!onDeviceTranslationCapabilities.contains(sourceFormat, targetFormat)) {
      return ImmutableSet.of();
    }
    return onDeviceTranslationCapabilities.get(sourceFormat, targetFormat);
  }

  public void setOnDeviceTranslationCapabilities(
      int sourceFormat, int targetFormat, Set<TranslationCapability> capabilities) {
    onDeviceTranslationCapabilities.put(
        sourceFormat, targetFormat, ImmutableSet.copyOf(capabilities));
  }

  @Resetter
  public static void reset() {
    onDeviceTranslationCapabilities.clear();
  }
}
