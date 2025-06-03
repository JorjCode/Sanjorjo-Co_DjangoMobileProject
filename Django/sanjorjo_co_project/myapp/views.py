from django.shortcuts import render
from django.http import HttpResponse
from .models import userData
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import userData
from .serializers import UserDataSerializer

# Create your views here.

def home(request):  
    return render(request, 'index.html')

def result(request):
    name=''
    if request.method == 'POST':
        
        email = request.POST.get("email")
        password = request.POST.get("password")
        confirmPass = request.POST.get('confirmPassword')
        name = request.POST.get("name")

        userdata = userData(name= name, email=email, password=password, confirmPass=confirmPass )
        #userdata.save()

        userData.objects.create(
            name=name,
            email=email,
            password=password,
            confirmPass=confirmPass
        )
    all_users = userData.objects.all()

    return render(request, 'result.html', {'users':all_users})

@api_view(['POST'])
def save_user_data(request):
    serializer = UserDataSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response({'message': 'Data saved successfully'})
    return Response(serializer.errors, status=400)
@api_view(['POST'])
def save_user(request):
    name = request.data.get('name')
    email = request.data.get('email')
    password = request.data.get('password')
    confirm_pass = request.data.get('confirmPass')
    # Save to DB logic...
    return Response({"message": "User saved successfully"})
    