package parsers;

import model.*;
import model.exceptions.EmptyStringException;
import parsers.exceptions.ParsingException;

import java.util.*;

public class TagParser extends Parser {
    public TagParser() {
    }

    @Override
    public void parse(String input, Task task) throws ParsingException {
        // delete duplicate elements
        Set<String> setElement = new HashSet();
        String[] arrElement;
        String strDescription = "";

        strDescription = separateStringToSet(input, setElement, strDescription, task);

        try {
            task.setDescription(strDescription);
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }


        ArrayList<String> arrDuedate = new ArrayList<>();
        ArrayList<String> arrStatus = new ArrayList<>();
        ArrayList<String> arrPriority = new ArrayList<>();
        ArrayList<String> arrTags = new ArrayList<>();

        //according to the order from element, put each element inside the array
        categorizeElement(setElement, arrDuedate, arrStatus, arrPriority, arrTags);

        setPriority(task, arrPriority);

        setDueDate(task, arrDuedate, arrTags);

        setStatus(task, arrStatus, arrTags);

        setTags(task, arrTags);

    }



    private void categorizeElement(Set<String> setElement,
                                   ArrayList<String> arrDuedate,
                                   ArrayList<String> arrStatus,
                                   ArrayList<String> arrPriority,
                                   ArrayList<String> arrTags) {
        for (String ele : setElement) {

            if (ele.equals("important") || ele.equals("urgent")) {
                arrPriority.add(ele);
            } else if (ele.equals("today") || ele.equals("tomorrow")) {
                arrDuedate.add(ele);
            } else if (ele.equals("to do") || ele.equals("up next")
                    || ele.equals("in progress") || ele.equals("done")) {
                arrStatus.add(ele);
            } else {
                arrTags.add(ele);
            }

        }
    }

    private String separateStringToSet(String input, Set<String> setElement,
                                       String strDescription, Task task) throws ParsingException {
        String[] arrElement = new String[]{};
        if (input.contains("##")) {
            // get description first
//            String[] arrInput = input.split("##");
            strDescription = input.split("##")[0];

            //split the rest of string either by ;
            if (input.split("##").length >= 2) {
//                String newInput = input.split("##")[1].split(";");
                arrElement = input.split("##")[1].split(";");
            }

        } else {
            // there is no ## sign
            try {
                task.setDescription(input);
                this.description = input;
            } catch (EmptyStringException e) {
                e.printStackTrace();
            }

            //throws ParsingException if description does not contain the tag deliminator
            throw new ParsingException("Parsing Error");
        }

        //push arrayList to non-duplicated set
        arrToSet(setElement, arrElement);

        this.description = strDescription;


        return strDescription;
    }

    private void arrToSet(Set<String> setElement, String[] arrElement) {
        for (String var :
                arrElement) {
            // delete space and convert to lower case
            setElement.add(var.trim().toLowerCase());
        }
    }

    private void setTags(Task task, ArrayList<String> arrTags) {
        if (arrTags.size() == 0) {
            //task.addTag(null);
            // no tag do nothing
        } else {
            for (String t :
                    arrTags) {
                try {
                    task.addTag(t);
                } catch (EmptyStringException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setStatus(Task task, ArrayList<String> arrStatus, ArrayList<String> arrTags) {
        if (arrStatus.size() == 0) {
            //default
            task.setStatus(Status.TODO);
        } else {
            Status st = matchStatus(arrStatus.get(0));
            task.setStatus(st);

            // put the reset to the tags
            for (int i = 0; i < arrStatus.size(); i++) {
                if (i > 0) {
                    arrTags.add(arrStatus.get(i));
                }
            }
        }
    }

    private void setDueDate(Task task, ArrayList<String> arrDuedate, ArrayList<String> arrTags) {
        if (arrDuedate.size() == 0) {
            task.setDueDate(null);
        } else {
            if (arrDuedate.get(0).equals("today")) {
                // today
                task.setDueDate(new DueDate(getTodayDate()));
            } else {
                //tomorrow
                task.setDueDate(new DueDate(getTomorrowDate()));
            }

            // put the reset to the tags
            for (int i = 0; i < arrDuedate.size(); i++) {
                if (i > 0) {
                    arrTags.add(arrDuedate.get(i));
                }
            }
        }
    }

    private void setPriority(Task task, ArrayList<String> arrPriority) {
        Priority p = new Priority();
        if (arrPriority.size() == 0) {
            // no priority setup, default p
            task.setPriority(p);
        } else {

            //set new Priority
            for (int i = 0; i < arrPriority.size(); i++) {
                if (arrPriority.get(i).equals("important")) {
                    p.setImportant(true);
                } else if (arrPriority.get(i).equals("urgent")) {
                    p.setUrgent(true);
                }
            }

            task.setPriority(p);
        }
    }

    private Status matchStatus(String s) {
        if (s.equals("to do")) {
            return Status.TODO;
        } else if (s.equals("up next")) {
            return Status.UP_NEXT;
        } else if (s.equals("in progress")) {
            return Status.IN_PROGRESS;
        } else if (s.equals("done")) {
            return Status.DONE;
        }
        return Status.TODO;
    }

    private Date getTodayDate() {
        // today 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();

    }

    private Date getTomorrowDate() {
        // today 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }
}
