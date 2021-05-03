# Simple To-do List Scheduler
This application is designed to schedule weekly to-do lists in an optimized way, with a simple GUI made using JAVAFX, there is no maximum limit for task items. The program will optimize the schedule according to the task priority, and the maximum number of tasks that could be fitted in the schedule for the same priority.
Also, the user has the privilege to add the item with a “From/To” format if the time is specific through the day, or in a “Hours” format if it just should be done through the day.

## User Inputs
Item Specifications:
Name - Day - Priority - From/To or Hours (All in 24 hour format)
No maximum limit on number of Items.

## Output
Weekly schedule with Task Items.

## Scheduler Policy
1) Tasks with higher priority should be added first
2) Tasks with same priority and have conflict (time overlapping): the scheduler will choose the task which maximize the number of added task through the day using “Earliest Finish Time Algorithm”.
3) Tasks with unspecified time boundary (just hours) have lower priority than the “From/To” tasks and will be fitted in the schedule to maximize the number of added Items (Shortest task first in the shortest free slot).