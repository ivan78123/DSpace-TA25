package org.dspace.app.xmlui.aspect.submission.submit;
import org.dspace.app.xmlui.aspect.submission.AbstractSubmissionStep;
import org.dspace.app.xmlui.utils.UIException;
import org.dspace.app.xmlui.wing.Message;
import org.dspace.app.xmlui.wing.WingException;
import org.dspace.app.xmlui.wing.element.Body;
import org.dspace.app.xmlui.wing.element.CheckBox;
import org.dspace.app.xmlui.wing.element.Division;
import org.dspace.app.xmlui.wing.element.List;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.Collection;
import org.dspace.content.Item;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.SQLException;


public class WatermarkStep extends AbstractSubmissionStep {


    /** Language Strings **/
    protected static final Message T_head =
            message("xmlui.Submission.submit.WatermarkStep.head");

    protected static final Message T_decision_label =
            message("xmlui.Submission.submit.WatermarkStep.decision_label");

    protected static final Message T_decision_checkbox =
            message("xmlui.Submission.submit.WatermarkStep.decision_checkbox");

    protected static final Message T_submit_remove =
            message("xmlui.Submission.submit.WatermarkStep.submit_remove");

    public WatermarkStep() {
        this.requireSubmission = true;
        this.requireStep = true;
    }

    @Override
    public void addBody(Body body) throws SAXException, WingException,
            UIException, SQLException, IOException, AuthorizeException {

        Item item = submission.getItem();
        Collection collection = submission.getCollection();
        String actionURL = contextPath + "/handle/" + collection.getHandle() + "/submit/" + knot.getId() + ".continue";

        Division div = body.addInteractiveDivision("submit-watermark", actionURL, Division.METHOD_POST, "primary submission");
        div.setHead(T_submission_head);
        addSubmissionProgressList(div);

        Division inner = div.addDivision("submit-watermark-inner");
        List controls = inner.addList("submit-review", List.TYPE_FORM);

        CheckBox decision = controls.addItem().addCheckBox("decision");
        decision.setLabel(T_decision_label);
        decision.addOption("accept",T_decision_checkbox);

        addControlButtons(controls);

    }

    /**
     * Each submission step must define its own information to be reviewed
     * during the final Review/Verify Step in the submission process.
     * <p>
     * The information to review should be tacked onto the passed in
     * List object.
     * <p>
     * NOTE: To remain consistent across all Steps, you should first
     * add a sub-List object (with this step's name as the heading),
     * by using a call to reviewList.addList().   This sublist is
     * the list you return from this method!
     *
     * @param reviewList The List to which all reviewable information should be added
     * @return The new sub-List object created by this step, which contains
     * all the reviewable information.  If this step has nothing to
     * review, then return null!
     * @throws SAXException       whenever.
     * @throws WingException      whenever.
     * @throws UIException        whenever.
     * @throws SQLException       whenever.
     * @throws IOException        whenever.
     * @throws AuthorizeException whenever.
     */
    @Override
    public List addReviewSection(List reviewList) throws SAXException, WingException, UIException, SQLException, IOException, AuthorizeException {
        return null;
    }


}
