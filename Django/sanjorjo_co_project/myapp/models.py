from django.db import models

# Create your models here.
class userData(models.Model):
    name = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    password = models.CharField(max_length=10)
    confirmPass = models.CharField(max_length=10)
    
    def __str__(self):
        return self.name

