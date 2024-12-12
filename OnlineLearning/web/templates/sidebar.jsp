 <div class="sidebar bg-light" style="width: 300px; height: 100vh; display: flex; flex-direction: column; justify-content: flex-start; align-items: center; padding-top: 100px;">
                <!-- Avatar and User Info -->
                <div class="text-center mb-4">
                    <!-- S? d?ng ?nh ??i di?n t? ??i t??ng user -->
                    <img src="${user.img}" alt="User Avatar" class="rounded-circle" style="width: 120px; height: 120px;">
                    <!-- S? d?ng tên ng??i dùng t? ??i t??ng user -->
                    <h3 class="mt-2 text-primary" style="font-size: 1.5rem;">${user.name}</h3>

                </div>

                <!-- User Info Links -->
                <div class="px-3">

                    <form action="ManagerCourses" method="GET" style="display: inline;">
                        <input type="hidden" name="status" value="2">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">List Courses</button>
                    </form>
                    <!-- Completed Courses và Studied Courses: S? d?ng POST -->
                       <form action="AddCourses" method="GET" style="display: inline;">
                        
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Add Courses</button>
                    </form>
                    
                    
                    <form action="AddLessonToCourses" method="GET" style="display: inline;">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Add Lesson To Courses</button>
                    </form>
                    <form action="ListLesson" method="GET" style="display: inline;">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">List Lesson</button>
                    </form>




                    <form action="ListMyCourses" method="GET" style="display: inline;">
                        <input type="hidden" name="status" value="3">
                        <button type="submit" class="d-block py-2 text-primary" style="font-size: 1.2rem; background: none; border: none;">Support</button>
                    </form>
                </div>
            </div>