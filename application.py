from tkinter import*
master=Tk()
txt = StringVar()
listbox=Listbox(master)
listbox.pack()
for item in["HDCA","HDCP","DMO","DOTNET"]:
    listbox.insert(END,item)
    def callback():
        txt.set(listbox.get(ACTIVE))
        label(master,text="Selected item:").pack()
        Entry(master,text="",textvariable=txt).pack()
        button=Button(master,text="press",command=callback)
        button.pack()
        mainloop()



























