from django.urls import path
from . import views
from .views import save_user_data
urlpatterns = [
    #path('admin/', admin.site.urls),
    path('', views.home),
    path('result', views.result, name='result'),
    path('api/save/', views.save_user_data, name='save_user_data'),

]
