package org.sylfra.idea.plugins.revu.ui.actions.review;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import org.sylfra.idea.plugins.revu.model.Review;

import javax.swing.*;

/**
 * @author <a href="mailto:sylfradev@yahoo.fr">Sylvain FRANCOIS</a>
 * @version $Id$
 */
abstract class AbstractReviewSettingsAction extends AnAction
{
  protected AbstractReviewSettingsAction()
  {
  }

  protected AbstractReviewSettingsAction(String text)
  {
    super(text);
  }

  protected AbstractReviewSettingsAction(String text, String description, Icon icon)
  {
    super(text, description, icon);
  }

  @Override
  public void update(AnActionEvent e)
  {
    if (isEnabledOnlyForNonEmbedded())
    {
      boolean enabled = false;

      JList liReviews = (JList) e.getData(DataKeys.CONTEXT_COMPONENT);
      if (liReviews != null)
      {
        Review selectedReview = (Review) liReviews.getSelectedValue();
        if ((selectedReview != null) && (!selectedReview.isEmbedded()))
        {
          enabled = true;
        }
      }

      e.getPresentation().setEnabled(enabled);
    }
  }

  protected abstract boolean isEnabledOnlyForNonEmbedded();
}